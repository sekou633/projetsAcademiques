#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>
#include <signal.h>    
#include <ctype.h>
#include <time.h> 
#include <sys/time.h>
#include <sys/types.h> 
#include <stdbool.h>

#include "serverHeader.h"


/**
 * @description: Fonction pour nettoyer les threads client lorsqu'ils se terminent
 * @param arg: socket du client
 * @return void
 */
void cleanup_thread(void* arg) {
    int client_socket = *((int*)arg);
    close(client_socket);
    pthread_mutex_lock(&clients_mutex);
    int index = findClient(client_socket);
    if (index != -1) {
        updateConnectedClients(clients, &client_count, index);
    }
    pthread_mutex_unlock(&clients_mutex);
    free(arg);
    pthread_exit(NULL);
}

/**
 * @description: Fonction pour nettoyer les salons de discussion lors de la fermeture du serveur
 * @return void
 */
void cleanup_chat_rooms() {
    pthread_mutex_lock(&chat_rooms_mutex);
    for (int i = 0; i < chat_room_count; i++) {
        // Libérer la mémoire pour chaque salon
        chat_rooms[i].client_count = 0;
    }
    pthread_mutex_unlock(&chat_rooms_mutex);
    pthread_mutex_destroy(&chat_rooms_mutex);
}

/**
 * @description: Fonction pour gérer la fermeture du serveur
 * @param signal le signal reçu pour la fermeture du serveur
 * @return true si le signal est SIGINT, false sinon
 */
void handle_server_shutdown(int signal) {
    if (signal == SIGINT)
    {
        time_t end_time;
        time(&end_time);
        printf("\nFermeture en cours...\n");
        cleanup_chat_rooms();
        sleep(DISCONNECT_TIMEOUT);
        printf("Fermeture terminée à %s", ctime(&end_time));
        exit(EXIT_SUCCESS);
    }
}

/**
 * @description: Fonction pour trouver l'indice d'un client dans la liste des clients connectés
 * @param client_socket le socket du client
 * @return l'indice du client dans la liste des clients connectés, -1 sinon
 */
int findClient(int client_socket) {
    for (int i = 0; i < client_count; i++) {
        if (clients[i].socket == client_socket) {
            return i;
        }
    }
    return -1;
}

/**
 * @description: Fonction pour trouver l'indice d'un groupe dans la liste des groupes créés
 * @param groupName le nom du groupe
 * @return l'indice du groupe dans la liste des groupes créés, -1 sinon
 */
int findGroupIndex(char *groupName) {
    pthread_mutex_lock(&chat_rooms_mutex);
    for (int i = 0; i < chat_room_count; i++) {
        if (strcmp(chat_rooms[i].name, groupName) == 0) {
            return i;
        }
    }
    pthread_mutex_unlock(&chat_rooms_mutex);
    return -1;
}

/**
 * @description: Fonction pour vérifier si un client est membre d'un groupe
 * @param groupIndex l'indice du groupe dans la liste des groupes créés
 * @param client_socket le socket du client
 * @return true si le client est membre du groupe, false sinon
 */
bool isClientInGroup (int groupIndex, int client_socket) {
    if (groupIndex == -1) {
        return false;
    }
    for (int i = 0; i < chat_rooms[groupIndex].client_count; i++) {
        if (chat_rooms[groupIndex].clients[i].socket == client_socket) {
            return true;
        }
    }
    return false;
}

void sendGroupMessage(char *groupName, char *message, int sender) {
    int i;
    for (i = 0; i < chat_room_count; i++) {
        int n = strcmp(chat_rooms[i].name, groupName);
        if (n == 0) {
            // Le groupe a été trouvé, envoyez le message à tous les membres
            // vérifier si l'expéditeur est membre du groupe
            if (!isClientInGroup(i, sender)) {
                char errorMessage[MAX_MSG_LEN];
                snprintf(errorMessage, sizeof(errorMessage), "Vous ne faites pas partie du groupe %s", groupName);
                send(sender, errorMessage, strlen(errorMessage), 0);
                return;
            }
            int j;
            for (j = 0; j < chat_rooms[i].client_count; j++) {
                // Envoyer le message à chaque membre sauf l'expéditeur
                if (chat_rooms[i].clients[j].socket != sender) {
                    char fullMessage[MAX_MSG_LEN];
                    snprintf(fullMessage, sizeof(fullMessage), "Message de %s : %s", clients[findClient(sender)].username, message);
                    send(chat_rooms[i].clients[j].socket, fullMessage, strlen(fullMessage), 0);
                }
            }
            return;
        }
    }

    // Le groupe n'a pas été trouvé, informez l'expéditeur
    char errorMessage[MAX_MSG_LEN];
    snprintf(errorMessage, sizeof(errorMessage), "Le groupe %s n'existe pas", groupName);
    send(sender, errorMessage, strlen(errorMessage), 0);
}

// Fonction pour gérer la commande /sendMsg
void handleSendMsg(char *command, int sender) {
    // Extraction des paramètres de la commande
    char groupName[50];
    char message[MAX_MSG_LEN];
    if (sscanf(command, "/sendMsg %s %[^\n]", groupName, message) != 2) {
        // La commande n'a pas les paramètres attendus
        char errorMessage[MAX_MSG_LEN];
        snprintf(errorMessage, sizeof(errorMessage), "Format incorrect. Utilisation : /sendMsg <nom du groupe> <message>");
        send(sender, errorMessage, strlen(errorMessage), 0);
        return;
    }
    
    sendGroupMessage(groupName, message, sender);
}

/**
 * @description: Fonction pour créer un groupe
 * @param room_name nom du groupe à créer
 * @param client_index indice du client qui crée le groupe
 * @requires: le nombre de groupes créés doit être inférieur au nombre maximal de groupes
 * @ensure: le groupe est créé avec succès
 * @return void
 */
void createChatRoom(char *room_name, int client_index) {
    if (chat_room_count >= MAX_CHAT_ROOMS) {
        send(clients[client_index].socket, "Limite maximale de groupes atteinte", strlen("Limite maximale de groupes atteinte"), 0);
        return;
    }

    for (int i = 0; i < chat_room_count; i++) {
        if (strcmp(chat_rooms[i].name, room_name) == 0) {
            send(clients[client_index].socket, "Ce groupe existe déjà. Essayez un autre nom.", strlen("Ce groupe existe déjà. Essayez un autre nom."), 0);
            return;
        }
    }

    strcpy(chat_rooms[chat_room_count].name, room_name);
    chat_rooms[chat_room_count].client_count = 0;
    chat_rooms[chat_room_count].clients[0] = clients[client_index];
    chat_rooms[chat_room_count].client_count++;
    chat_room_count++;
    char createMessage[MAX_MSG_LEN];
    snprintf(createMessage, sizeof(createMessage), "Le groupe %s a été créé avec succès !", room_name);
    send(clients[client_index].socket, createMessage, strlen(createMessage), 0);
}

/**
 * @description: Fonction pour rejoindre un groupe
 * @param room_name nom du groupe à rejoindre
 * @param client_index indice du client qui rejoint le groupe
 * @requires: le groupe doit exister
 * @requires: le nombre de membres du groupe doit être inférieur au nombre maximal de membres
 * @ensure: le client rejoint le groupe avec succès
 * @return void
 */
void joinChatRoom(char *room_name, int client_index) {
    int room_index = -1;
    for (int i = 0; i < chat_room_count; i++) {
        if (strcmp(chat_rooms[i].name, room_name) == 0) {
            room_index = i;
            break;
        }
    }

    if (room_index == -1) {
        send(clients[client_index].socket, "Ce groupe n'existe pas. Veuillez vérifier le nom.", strlen("Ce groupe n'existe pas. Veuillez vérifier le nom."), 0);
        return;
    }

    if (chat_rooms[room_index].client_count >= MAX_CLIENTS_IN_ROOM) {
        send(clients[client_index].socket, "Le groupe est complet. Réessayez plus tard.", strlen("Le groupe est complet. Réessayez plus tard."), 0);
        return;
    }

    chat_rooms[room_index].clients[chat_rooms[room_index].client_count] = clients[client_index];
    chat_rooms[room_index].client_count++;
    char joinMessage[MAX_MSG_LEN];
    snprintf(joinMessage, sizeof(joinMessage), "Vous avez rejoint le groupe %s avec succès !", room_name);
    send(clients[client_index].socket, joinMessage, strlen(joinMessage), 0);
}

/**
 * @description: Fonction pour quitter un groupe
 * @param room_name nom du groupe à quitter
 * @param client_index indice du client qui quitte le groupe
 * @requires: le groupe doit exister
 * @requires: le client doit être membre du groupe
 * @ensure: le client quitte le groupe avec succès
 * @return void
 */
void leaveChatRoom(char *room_name, int client_index) {
    int room_index = -1;
    for (int i = 0; i < chat_room_count; i++) {
        if (strcmp(chat_rooms[i].name, room_name) == 0) {
            room_index = i;
            break;
        }
    }

    if (room_index == -1) {
        send(clients[client_index].socket, "Ce groupe n'existe pas. Veuillez vérifier le nom.", strlen("Ce groupe n'existe pas. Veuillez vérifier le nom."), 0);
        return;
    }

    int client_in_room = 0;
    for (int i = 0; i < chat_rooms[room_index].client_count; i++) {
        if (clients[client_index].socket == chat_rooms[room_index].clients[i].socket) {
            client_in_room = 1;
            break;
        }
    }

    if (!client_in_room) {
        send(clients[client_index].socket, "Vous ne faites pas partie de ce groupe.", strlen("Vous ne faites pas partie de ce groupe."), 0);
        return;
    }

    // Supprimez le client du salon
    for (int i = 0; i < chat_rooms[room_index].client_count; i++) {
        if (clients[client_index].socket == chat_rooms[room_index].clients[i].socket) {
            for (int j = i; j < chat_rooms[room_index].client_count - 1; j++) {
                chat_rooms[room_index].clients[j] = chat_rooms[room_index].clients[j + 1];
            }
            chat_rooms[room_index].client_count--;
            char leaveMessage[MAX_MSG_LEN];
            snprintf(leaveMessage, sizeof(leaveMessage), "Vous avez quitté le groupe %s avec succès !", room_name);
            send(clients[client_index].socket, leaveMessage, strlen(leaveMessage), 0);
            return;
        }
    }
}

/**
 * @description: Fonction pour afficher les membres d'un groupe
 * @param room_name nom du groupe
 * @param client_index indice du client qui demande la liste des membres du groupe
 * @requires: le groupe doit exister
 * @requires: le client doit être membre du groupe
 * @ensure: le client reçoit la liste des membres du groupe avec succès
 * @return void
 */
void showChatRoomMembers(char *room_name, int client_index) //
{
    int room_index = -1;
    for (int i = 0; i < chat_room_count; i++) {
        if (strcmp(chat_rooms[i].name, room_name) == 0) {
            room_index = i;
            break;
        }
    }

    if (room_index == -1) {
        send(clients[client_index].socket, "Ce groupe n'existe pas. Veuillez vérifier le nom.", strlen("Ce groupe n'existe pas. Veuillez vérifier le nom."), 0);
        return;
    }

    int client_in_room = 0; // Vérifiez si le client est membre du groupe
    for (int i = 0; i < chat_rooms[room_index].client_count; i++) {
        if (clients[client_index].socket == chat_rooms[room_index].clients[i].socket) {
            client_in_room = 1;
            break;
        }
    }

    if (!client_in_room) {
        send(clients[client_index].socket, "Vous ne faites pas partie de ce groupe.", strlen("Vous ne faites pas partie de ce groupe."), 0);
        return;
    }

    char members_msg[MAX_MSG_LEN];
    memset(members_msg, 0, sizeof(members_msg)); 
    snprintf(members_msg, sizeof(members_msg), "Membres du groupe %s : ", room_name);

    for (int i = 0; i < chat_rooms[room_index].client_count; i++) {
        strcat(members_msg, chat_rooms[room_index].clients[i].username);
        if (i < chat_rooms[room_index].client_count - 1) {
            strcat(members_msg, ", ");
        }
    }

    send(clients[client_index].socket, members_msg, strlen(members_msg), 0);
}

/**
 * @description: Fonction pour recevoir la liste des groupes crées
 * @return char* 
 */
char* getGroupList() {
    char* list = malloc(1000 * sizeof(char)); // 
    if (list == NULL) {
        perror("Erreur d'allocation mémoire");
        exit(EXIT_FAILURE);
    }

    strcpy(list, "Liste des groupes crées : ");

    for (int i = 0; i < chat_room_count; i++) {
        strcat(list, chat_rooms[i].name);
        if (i < chat_room_count - 1) {
            strcat(list, ", ");
        }
    }

    return list;
}

/**
 * @description: Fonction pour supprimer un membre d'un groupe
 * @param chatRoomName nom du groupe dans lequel on veut supprimer un membre
 * @param client_socket socket du client qui demande la suppression du groupe
 * @param username nom du membre à supprimer
 * @requires: le client doit être le créateur du groupe
 * @requires: le groupe doit être non vide
 * @ensure: le membre est supprimé avec succès
 * @return void
 */
void removeChatRoomMember(char *chatRoomName, char *username, int client_socket) {
    pthread_mutex_lock(&chat_rooms_mutex);
    int chatRoomIndex = -1;
    for (int i = 0; i < chat_room_count; i++) {
        if (strcmp(chat_rooms[i].name, chatRoomName) == 0) {
            chatRoomIndex = i;
            break;
        }
    }
    if (chatRoomIndex != -1) {
        if (chat_rooms[chatRoomIndex].client_count == 1 && chat_rooms[chatRoomIndex].clients[0].socket == client_socket) {
            send(client_socket, "Vous êtes le seul membre du groupe, vous ne pouvez pas vous supprimer.", strlen("Vous êtes le seul membre du groupe, vous ne pouvez pas vous supprimer."), 0);
        } else {
            int clientIndex = -1;
            for (int i = 0; i < chat_rooms[chatRoomIndex].client_count; i++) {
                if (strcmp(chat_rooms[chatRoomIndex].clients[i].username, username) == 0) {
                    clientIndex = i;
                    break;
                }
            }
            if (clientIndex != -1) {
                for (int i = clientIndex; i < chat_rooms[chatRoomIndex].client_count - 1; i++) {
                    chat_rooms[chatRoomIndex].clients[i] = chat_rooms[chatRoomIndex].clients[i + 1];
                }
                chat_rooms[chatRoomIndex].client_count--;
            }
        }
    }
    pthread_mutex_unlock(&chat_rooms_mutex);
}


/**
 * @description: Fonction pour mettre à jour la liste des groupes crées
 * @param chatRoomName nom du groupe à supprimer
 * @param client_socket socket du client qui demande la suppression du groupe
 * @requires: le client doit être le créateur du groupe
 * @requires: le groupe doit être non vide
 * @ensure: le groupe est supprimé avec succès
 * @return void
 */

void updateCreatedGroups(struct ChatRoom *chat_rooms, int *chat_room_count, int index) {
    // Supprimer le groupe déconnecté de la liste des groupes créés
    for (int i = index; i < (*chat_room_count) - 1; i++) {
        chat_rooms[i] = chat_rooms[i + 1];
    }
    (*chat_room_count)--;
}



/**
 * @description: Fonction pour envoyer un message à tous les clients connectés au serveur
 * @param message le message à envoyer
 * @param sender_socket le socket du client qui envoie
 * @return void
 */
void broadcast(char *message, int sender_socket)
{
    pthread_mutex_lock(&clients_mutex);
    
    for (int i = 0; i < client_count; i++) {
        if (clients[i].socket != sender_socket) {
            send(clients[i].socket, message, strlen(message), 0);
        }
    }
    pthread_mutex_unlock(&clients_mutex);
}

/**
 * @description: Fonction pour envoyer un message à un client spécifique
 * @param message le message à envoyer
 * @param recipient le destinataire du message
 * @param client_socket le client qui envoie le message
 * @return void
 */
void unicast(char *message, char *recipient, int client_socket) // Send the message to the specified client
{
    pthread_mutex_lock(&clients_mutex);
    int not_find = 0;
    for (int i = 0; i < client_count; i++) {
        if (strcmp(clients[i].username, recipient) == 0) {
            send(clients[i].socket, message, strlen(message), 0);
            not_find = 1;
            break;
        }
    }
    if (not_find == 0) {
        char error_message[50];
        sprintf(error_message, "Le client %s n'existe pas ou n'est pas connecté !", recipient);
        send(client_socket, error_message, strlen(error_message), 0);
    }
    pthread_mutex_unlock(&clients_mutex);
}

/**
 * @description: Fonction pour mettre à jour la liste des clients connectés
 * @param clients la liste des clients connectés
 * @param client_count le nombre de clients connectés
 * @param index l'indice du client déconnecté
 * @return void
 */
void updateConnectedClients(struct ClientInfo *clients, int *client_count, int index) {
    // Supprimer le client déconnecté de la liste des clients connectés
    for (int i = index; i < (*client_count) - 1; i++) {
        clients[i] = clients[i + 1];
    }
    (*client_count)--;
}

/**
 * @description: Fonction pour notifier tous les clients qu'un client s'est déconnecté
 * @param clients la listes des clients connectés
 * @param client_count le nombre de clients connectés
 * @param index l'indice du client déconnecté
 * @return void
 */
void notifyClientDisconnection(struct ClientInfo *clients, int client_count, int index) {
    char disconnectMessage[MAX_MSG_LEN];
    snprintf(disconnectMessage, MAX_MSG_LEN, "Le client %s s'est déconnecté.", clients[index].username);

    for (int i = 0; i < client_count; i++) {
        if (i != index) {
            send(clients[i].socket, disconnectMessage, strlen(disconnectMessage), 0);
        }
    }
}

/**
 * @description: Fonction pour afficher les clients connectés
 * @param client_socket le socket du client qui demande la liste des clients connectés
 * @requires: client_socket doit être connecté au serveur
 * @ensure: le client reçoit la liste des clients connectés avec succès
 * @return void
 */
void handleListClients(int client_socket) {
    char clientList[MAX_MSG_LEN];
    strcpy(clientList, "Liste des clients connectés : ");

    pthread_mutex_lock(&clients_mutex);
    for (int i = 0; i < client_count; i++) 
    {
        if (clients[i].socket != client_socket)
        strcat(clientList, clients[i].username);
        if (i < client_count - 1) {
            strcat(clientList, " ");

        }
    }
    pthread_mutex_unlock(&clients_mutex);

    send(client_socket, clientList, strlen(clientList), 0);
}


/**
 * @description: Fonction pour gérer les commandes envoyées par les clients connectés au serveur
 * @param socket le socket du client
 * @return void
 */
void *handleClient(void *socket) {
    int client_socket = *((int *)socket);
    char buffer[MAX_MSG_LEN];
    int index;

    // Libérer la mémoire allouée pour le thread client lorsqu'il se termine
    pthread_cleanup_push(cleanup_thread, client_socket);

    if (recv(client_socket, buffer, sizeof(buffer), 0) <= 0)
    {
        close(client_socket);
        return NULL;
    }

    pthread_mutex_lock(&clients_mutex); 
    index = client_count; 
    clients[index].socket = client_socket;
    strcpy(clients[index].username, buffer);
    client_count++;
    pthread_mutex_unlock(&clients_mutex);

    printf("Client %s a rejoint le chat.\n", clients[index].username);

    while (1) // Boucle infinie pour recevoir les messages du client 
    {
        memset(buffer, 0, sizeof(buffer)); 
        int n = recv(client_socket, buffer, sizeof(buffer), 0);
        index = findClient(client_socket);
        
        if (n <= 0) 
        {
            close(client_socket); 
            printf("Client %s s'est déconnecté.\n", clients[index].username);
            notifyClientDisconnection(clients, client_count, index);
            pthread_mutex_lock(&clients_mutex);
            updateConnectedClients(clients, &client_count, index);
            pthread_mutex_unlock(&clients_mutex);
            break;
        }

        if (strncmp(buffer, "/broadcast", strlen("/broadcast")) == 0)
        {  
            // Commande pour envoyer un message en broadcast
            char *message = buffer + (strlen("/broadcast") + 1);
            if (message[0] == '\0' || isspace(message[0])) {
                send(client_socket, "La commande /broadcast nécessite un message non vide.", strlen("La commande /broadcast nécessite un message non vide."), 0);
            } else {
                char broadcastMessage[MAX_MSG_LEN];
                sprintf(broadcastMessage, "Broadcast de %s : %s", clients[index].username, message);
                broadcast(broadcastMessage, clients[index].socket); 
            }
            
           
        } else if (strncmp(buffer, "/unicast", strlen("/unicast")) == 0) {
            // Commande pour envoyer un message en unicast
            
            char *recipient = strtok(buffer + (strlen("/unicast") + 1), " "); 
            char *message = strtok(NULL, ""); 
            
            if (recipient == NULL || message == NULL || isspace(message[0])) {
                send(client_socket, "La commande /unicast nécessite un destinataire et un message.", strlen("La commande /unicast nécessite un destinataire et un message."), 0);
            
            } else if (strcmp(recipient, clients[index].username) == 0) {
                send(client_socket, "Vous ne pouvez pas vous envoyer un message à vous-même.", strlen("Vous ne pouvez pas vous envoyer un message à vous-même."), 0);
            } else {
                char unicastMessage[MAX_MSG_LEN];
                sprintf(unicastMessage, "Unicast de %s : %s", clients[index].username, message);
                unicast(unicastMessage, recipient, clients[index].socket); 
            }

        } else if (strncmp(buffer, "/list", strlen("/list")) == 0) {
            // Commande pour consulter la liste des clients connectés
            handleListClients(clients[index].socket); 
            continue;
        } else if (strncmp(buffer, "/exit", strlen("/exit")) == 0) {
            // Commande pour se déconnecter
            close(client_socket); // Close the socket to the client
            printf("Client %s s'est déconnecté.\n", clients[index].username);
            notifyClientDisconnection(clients, client_count, index); 
            pthread_mutex_lock(&clients_mutex); 
            updateConnectedClients(clients, &client_count, index); 
            pthread_mutex_unlock(&clients_mutex);
            break;

        } else if (strncmp(buffer, "/removeGroup", strlen("/removeGroup")) == 0) {
            // Commande pour supprimer un groupe
            char *chatRoomName = strtok(buffer + (strlen("/removeGroup") + 1), "\n");
            if (chatRoomName == NULL || isspace(chatRoomName[0])) {
                send(client_socket, "La commande /removeGroup nécessite un nom de groupe non vide.", strlen("La commande /removeGroup nécessite un nom de groupe non vide."), 0);
            } else {
               int index_group = findGroupIndex(chatRoomName);
               pthread_mutex_lock(&chat_rooms_mutex);
               updateCreatedGroups(chat_rooms, &chat_room_count, index_group);
                pthread_mutex_unlock(&chat_rooms_mutex);
            }
        } else if (strncmp(buffer, "/removeMember", strlen("/removeMember")) == 0) {
            // Commande pour supprimer un membre d'un groupe
            char *chatRoomName = strtok(buffer + (strlen("/removeMember") + 1), " ");
            char *username = strtok(NULL, "\n");
            if (chatRoomName == NULL || isspace(chatRoomName[0]) || username == NULL || isspace(username[0])) {
                send(client_socket, "La commande /removeMember nécessite un nom de groupe et un nom de membre non vide.", strlen("La commande /removeMember nécessite un nom de groupe et un nom de membre non vide."), 0);
            } else {
                pthread_mutex_lock(&chat_rooms_mutex);
                removeChatRoomMember(chatRoomName, username, client_socket);
                pthread_mutex_unlock(&chat_rooms_mutex);
            }
        } 
        else if (strncmp(buffer, "/whoami", strlen("/whoami")) == 0){
                char whoami[MAX_MSG_LEN];
                sprintf(whoami, "Votre nom d'utilisateur est %s", clients[index].username);
                send(clients[index].socket, whoami, strlen(whoami), 0);

        } else if (strncmp(buffer, "/create", strlen("/create")) == 0) {
            // Commande pour créer un groupe
            char *chatRoomName = strtok(buffer + (strlen("/create") + 1), "\n");
            if (chatRoomName == NULL || isspace(chatRoomName[0])) {
                send(client_socket, "La commande /create nécessite un nom de groupe non vide.", strlen("La commande /create nécessite un nom de groupe non vide."), 0);
            } else {
                pthread_mutex_lock(&chat_rooms_mutex);
                createChatRoom(chatRoomName, index);
                pthread_mutex_unlock(&chat_rooms_mutex);
            }
        } else if (strncmp(buffer, "/join", strlen("/join")) == 0) {
            // Commande pour rejoindre un groupe
            char *chatRoomName = strtok(buffer + (strlen("/join") + 1), "\n");
            if (chatRoomName == NULL || isspace(chatRoomName[0])) {
                send(client_socket, "La commande /join nécessite un nom de groupe non vide.", strlen("La commande /join nécessite un nom de groupe non vide."), 0);
            } else {
                pthread_mutex_lock(&chat_rooms_mutex);
                joinChatRoom(chatRoomName, index);
                pthread_mutex_unlock(&chat_rooms_mutex);
            }

        } else if (strncmp(buffer, "/leave", strlen("/leave")) == 0) {
            // Commande pour quitter un groupe
            char *chatRoomName = strtok(buffer + (strlen("/leave") + 1), "\n");
            if (chatRoomName == NULL || isspace(chatRoomName[0])) {
                send(client_socket, "La commande /leave nécessite un nom de groupe non vide.", strlen("La commande /leave nécessite un nom de groupe non vide."), 0);
            } else {
                pthread_mutex_lock(&chat_rooms_mutex);
                leaveChatRoom(chatRoomName, index);
                pthread_mutex_unlock(&chat_rooms_mutex);
            }
        } else if (strncmp(buffer, "/sendMsg", strlen("/sendMsg")) == 0)
        {
            handleSendMsg(buffer, clients[index].socket);
        }
         else if (strncmp(buffer, "/showMembers", strlen("/showMembers")) == 0) {
            // Commande pour afficher les membres d'un groupe
            char *chatRoomName = strtok(buffer + (strlen("/showMembers") + 1), "\n");
            if (chatRoomName == NULL || isspace(chatRoomName[0])) {
                send(client_socket, "La commande /showMembers nécessite un nom de groupe non vide.", strlen("La commande /showMembers nécessite un nom de groupe non vide."), 0);
            } else {
                pthread_mutex_lock(&chat_rooms_mutex);
                showChatRoomMembers(chatRoomName, index);
                pthread_mutex_unlock(&chat_rooms_mutex);
            }

        } else if (strncmp(buffer, "/showAllGroups", 14) == 0){
            char *list = getGroupList();

            if (strlen(list) == strlen("Liste des groupes : ")) {
                send(client_socket, "Aucun groupe n'est créé", strlen("Aucun groupe n'est créé"), 0);
            } else {
                send(client_socket, list, strlen(list), 0);
            }
            free(list);
       
        }
        else {
            
            char error_message[MAX_MSG_LEN];
            sprintf(error_message, "Commande inconnue (tapez /help) pour vous aidez: %s", buffer);
            send(clients[index].socket, error_message, strlen(error_message), 0);

        }
    }

    pthread_cleanup_pop(0); // Pop the cleanup handler from the cleanup stack and execute it
    pthread_exit(NULL); // Exit the thread
}
