#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>


#define PORT 50000
#define MAX_MSG_LEN 1024

int client_socket;
char username[50];

/**
 * @description: Cette fonction permet de recevoir les messages du serveur
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 * @author: <a href="mailto:gdaviddogbo@gmail.com"> David Guilavogui </a>
 * @version: 1.0
 * 
 * @param socket le descripteur pour recevoir les messages du serveur
 * @return void* 
 */

void *receiveMessage(void *socket) {
    int sockfd = *((int *)socket);
    char message[MAX_MSG_LEN];

    while (1) {
        memset(message, 0, sizeof(message));
        int n = recv(sockfd, message, MAX_MSG_LEN, 0);
        if (n <= 0) {
            printf("Le serveur s'est déconnecté ou une erreur s'est produite\n");
            exit(1);
        }

        // si le message indique une deconnexion 
        if (strncmp(message, "Le Client", 8) == 0 && strstr(message, "s'est déconnecté") != NULL) {
            printf("%s\n", message);
        }
        // si le message est pour afficher la liste des clients connectés
        else if (strncmp(message, "Liste des clients connectés : ", strlen("Liste des clients connectés : ")) == 0) {
            printf("%s\n", message);
        } 
        // si le message est pour afficher le nom d'utilisateur
        else if (strncmp(message, "Votre nom d'utilisateur est ", strlen("Votre nom d'utilisateur est ")) == 0) {
            printf("%s\n", message);
        }
        
        // si le message est pour afficher les membres d'un groupe
        else if (strncmp(message, "Membres du groupe ", strlen("Membres du groupe ")) == 0) {
            printf("%s\n", message);
        }
        // si le message est pour afficher tous les groupes
        else if (strncmp(message, "Liste des groupes : ", strlen("Liste des groupes : ")) == 0) {
            printf("%s\n", message);
        }
        else {
            printf("%s\n", message);
        }

    }
}

/**
 * @description: Cette fonction permet d'envoyer les messages au serveur
 * @param message le message à envoyer au serveur
 * @return void 
 */
void sendMessage(char *message) {
    send(client_socket, message, strlen(message), 0);
}

/**
 * @description: Cette fonction permet de créer un groupe
 * @param groupName le nom du groupe à créer
 * @return void 
 */
void createGroup(char *groupName) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/create %s", groupName);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet de rejoindre un groupe
 * @param groupName le nom du groupe à rejoindre
 * @return void 
 */
void joinGroup(char *groupName) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/join %s", groupName);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet de quitter un groupe
 * @param groupName le nom du groupe à quitter
 * @return void
 */
void leaveGroup(char *groupName) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/leave %s", groupName);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet d'afficher les membres d'un groupe
 * @param groupName le nom du groupe dont on veut afficher les membres
 * @return void
 */
void showGroupMembers(char *groupName) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/show %s", groupName);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet d'afficher tous les groupes
 * @return void
 */
void showAllGroups() {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/showAllGroups");
    sendMessage(command);
}

/**
 * @description: Cette fonction permet de supprimer un membre d'un groupe
 * @param groupName le nom du groupe dont on veut supprimer un membre
 * @param username le nom d'utilisateur du membre à supprimer
 * @return void
 */
void removeMemberFromGroup(char *groupName, char *username) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/remove %s %s", groupName, username);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet de supprimer un groupe
 * @param groupName le nom du groupe à supprimer
 * @return void
 */
void removeGroup(char *groupName) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/remove %s", groupName);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet d'envoyer un message dans un groupe auquel on appartient
 * @param groupName le nom du groupe dans lequel on veut envoyer le message
 * @param message le message à envoyer
 * @return void
 */
void sendGroupMessage(char *groupName, char *message) {
    char command[MAX_MSG_LEN];
    snprintf(command, sizeof(command), "/sendMsg %s %s", groupName, message);
    sendMessage(command);
}

/**
 * @description: Cette fonction permet de demander la confirmation de l'utilisateur
 * @return 1 si l'utilisateur confirme, 0 sinon
 */
int askConfirmation() {
    char choice;
    printf("Voulez-vous vraiment effectuer cette action ? (O/N): ");
    scanf(" %c", &choice);
    // Ignorer les autres caractères dans le buffer d'entrée
    while (getchar() != '\n');
    // Comparer le choix de l'utilisateur
    if (choice == 'O' || choice == 'o') {
        return 1;  // Confirmation
    } else {
        return 0;  // Annulation
    }
}

//================================================================
//======================= MAIN ===================================
//================================================================
/**
 * @description: Cette fonction permet de lancer le client
 * @category: main program
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 * @author: <a href="mailto:gdaviddogbo@gmail.com"> David Guilavogui </a>
 * @version: 1.0
 * 
 * @return int 0 si tout se passe bien et une autre valeur sinon
 */
int main() {
    struct sockaddr_in server_addr;
    pthread_t tid;

    client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (client_socket < 0) {
        perror("Erreur lors de la création du socket");
        exit(1);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    if (connect(client_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0) {
        perror("Erreur lors de la connexion au serveur");
        exit(1);
    }

    printf("Entrez votre nom d'utilisateur : ");
    fgets(username, sizeof(username), stdin);
    // Ne pas permettre à l'utilisateur de saisir un nom d'utilisateur vide
    while (strlen(username) <= 1 || strlen(username) > 20) {
        printf("Le nom d'utilisateur ne peut pas être vide et ne peut pas dépasser 20 caractères.\nVeuillez réessayer : ");
        fgets(username, sizeof(username), stdin);
    }

    // Ne pas permettre à l'utilisateur de saisir un nom d'utilisateur contenant des caractères spéciaux
    while (strpbrk(username, " ~!@#$%^&*()+-={}[]|\\:;\"'<>,.?/") != NULL) {
        printf("Le nom d'utilisateur ne peut pas contenir de caractères spéciaux ni d'espaces.\nVeuillez réessayer : ");
        fgets(username, sizeof(username), stdin);
    }

    username[strlen(username) - 1] = '\0';

    send(client_socket, username, strlen(username), 0);

    pthread_create(&tid, NULL, receiveMessage, &client_socket);

    printf("Bienvenue dans le chat, %s !\n", username);

    // Nettoyer l'écran et afficher un menu à l'utilisateur

    char input[MAX_MSG_LEN];

    while (1) {
        

        fgets(input, sizeof(input), stdin);

        if (strncmp(input, "/broadcast", 10) == 0) {
            // Commande pour envoyer un message en broadcast
            sendMessage(input);
        } else if (strncmp(input, "/unicast", 8) == 0) {
            // Commande pour envoyer un message en unicast
            sendMessage(input);

        } else if (strncmp(input, "/inbox", 6) == 0) {
            // Commande pour consulter la boîte de réception
            send(client_socket, input, strlen(input), 0);

        } else if (strncmp(input, "/help", 5) == 0) {
            // Commande pour afficher l'aide
            // sendMessage(input);
            printf("***************************************************************************************************************\n");
            printf("  /broadcast <message> : envoyer un message en broadcast\n");
            printf("  /unicast <destinataire> <message> : envoyer un message en unicast\n");
            printf("  /list : afficher la liste des clients connectés\n");
            printf("  /whoami : afficher le nom d'utilisateur\n");
            printf("  /create <nom de groupe> : créer un groupe\n");
            printf("  /join <nom de groupe> : rejoindre un groupe\n");
            printf("  /sendMsg <nom du groupe> <message> : envoyer un message dans un groupe auquel on appartient\n");
            printf("  /leave <nom de groupe> : quitter un groupe\n");
            printf("  /showMembers <nom de groupe> : afficher les membres d'un groupe\n");
            printf("  /showAllGroups : afficher tous les groupes\n");
            printf("  /help : afficher l'aide\n");
            printf("  /clear : pour nettoyer l'écran\n");
            printf("  /exit : se déconnecter\n");
            printf("****************************************************************************************************************\n");
        } else if (strncmp(input, "/clear", 6) == 0) {
            // Commande pour effacer l'écran
            system("clear");

        } else if (strncmp(input, "/list", 5) == 0) {
            // Commande pour afficher la liste des clients connectés
            sendMessage(input);

        } else if (strncmp(input, "/whoami", 7) == 0) {
            // Commande pour afficher le nom d'utilisateur
            sendMessage(input);

        } else if (strncmp(input, "/create", 7) == 0) {
            // Commande pour créer un groupe
            
            sendMessage(input);

        } else if (strncmp(input, "/join", 5) == 0) {
            // Commande pour rejoindre un groupe
            sendMessage(input);

        } else if (strncmp(input, "/sendMsg", 8) == 0) {
            
            sendMessage(input);

        } else if (strncmp(input, "/leave", 6) == 0) {
            // Commande pour quitter un groupe
            if (askConfirmation()) {
                sendMessage(input);
            }
            else {
                printf("Action annulée.\n");
            }

        } else if (strncmp(input, "/showMembers", strlen("/showMembers")) == 0) {
            // Commande pour afficher les membres d'un groupe
            sendMessage(input);

        } else if (strncmp(input, "/showAllGroups", 14) == 0) {
            // Commande pour afficher tous les groupes
            showAllGroups();
        } else if (strncmp(input, "/removeGroup", strlen("/removeGroup")) == 0) {
            // Commande pour supprimer un groupe
            sendMessage(input);

        } else if (strncmp(input, "/exit", 5) == 0) {
            // Commande pour se déconnecter
            if (askConfirmation()) {
                sendMessage(input);
                close(client_socket);
                exit(0);
            }
            else {
                printf("Action annulée.\n");
            }
            
        } else {
            // Envoyer le texte saisi comme un message en broadcast par défaut
            sendMessage(input);
        }
    }

    return 0;
}
