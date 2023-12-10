// ----------------------------------------------------------------
// Description: Header file for serverHeader.c
// ----------------------------------------------------------------

#ifndef SERVERHEADER_H
#define SERVERHEADER_H

// #include "constantes.h"

//===============================================================================
//=========================  DEFINITION DES CONSTANTES ==========================
//===============================================================================
#define PORT 50000 // port number
#define MAX_MSG_LEN 1024 // max message size
#define MAX_CLIENTS 10 // number of clients connected to the server
#define DISCONNECT_TIMEOUT 5 // 5 seconds
#define MAX_CLIENTS_IN_ROOM 10 // number of clients in a chat room (default: 10)
#define MAX_CHAT_ROOMS 10 // number of chat rooms (default: 10) 
//================================================================================

//===============================================================================
//=========================  DEFINITION DES STRUCTURES ==========================
//===============================================================================

struct ClientInfo {
    int socket;
    char username[50];
};

struct ChatRoom {
    char name[50];
    struct ClientInfo clients[MAX_CLIENTS_IN_ROOM];
    int client_count;
};

extern struct ChatRoom chat_rooms[MAX_CHAT_ROOMS];
extern int chat_room_count;


extern struct ClientInfo clients[MAX_CLIENTS];
extern int client_count;


// Mutex pour la gestion de la liste des clients
// extern pthread_mutex_t clients_mutex;
extern pthread_mutex_t clients_mutex;
// Mutex pour la gestion de la liste des groupes créés par les clients
// extern pthread_mutex_t chat_rooms_mutex;
extern pthread_mutex_t chat_rooms_mutex;

void cleanup_thread(void* arg);
void cleanup_chat_rooms();
void handle_server_shutdown(int signal);
int findClient(int client_socket);
int findGroupIndex(char *groupName);
bool isClientInGroup (int groupIndex, int client_socket);
void sendGroupMessage(char *groupName, char *message, int sender);
void handleSendMsg(char *command, int sender);
void createChatRoom(char *room_name, int client_index);
void joinChatRoom(char *room_name, int client_index);
void leaveChatRoom(char *room_name, int client_index);
void showChatRoomMembers(char *room_name, int client_index);
char* getGroupList();
void removeChatRoomMember(char *chatRoomName, char *username, int client_socket);
void removeGroup(char *chatRoomName, int client_socket);
void broadcast(char *message, int sender_socket);
void unicast(char *message, char *recipient, int client_socket);
void updateConnectedClients(struct ClientInfo *clients, int *client_count, int index);
void notifyClientDisconnection(struct ClientInfo *clients, int client_count, int index);
void handleListClients(int client_socket);
void *handleClient(void *socket);



#endif