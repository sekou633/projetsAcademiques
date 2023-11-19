//**************************************/
//  Importing React Native Components
//*****************************************/

import React ,{useEffect, useState, useContext}  from "react";
import { StyleSheet, View, TextInput, KeyboardAvoidingView, Text, FlatList, Platform, Dimensions, Pressable, Modal, Alert} from 'react-native';
import TodoItem from "./TodoItem";
import Boutton from "./Boutton";
import ProgressBar from "./ProgressBar";

import { UsernameContext, TokenContext} from "../Context/Context";
import { getTasks, createTask, deleteTasks, updateTasks, markTask } from "../API/todoAPI";


//*****************************************/
//  TodoList Component 
//*****************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @returns TodoList component with a TextInput, a ProgressBar, a FlatList and a Button
 */

export default function TodoList(){

    //******************************************/
    //  Initializing States
    //******************************************/

        const [todoList, setTodoList] = useState([]);
        const [copyTodo, setCopyTodo] = useState(todoList);
        const [count, setCount] = useState(todoList.filter((item) => item.done).length);
        const [newTodoText, setNewTodoText] = useState('');
        const [submited, setSubmited] = useState(false);
        const [token, setToken] = useContext(TokenContext);
        const [username, setUsername] = useContext(UsernameContext);
        const [modalVisible, setModalVisible] = useState(false);


        //******************************************/
        //  useEffect Hook
        //******************************************/

        useEffect(() => {
            getTasks(username, token).then((data) => {
                const newList = data.map((item) => ({id: item.id, content: item.title, done: item.done}));
                setTodoList(newList);
            });
        }, []);

        useEffect(() => {
            setCount(todoList.filter((item) => item.done).length);
        }, [todoList]);

        useEffect(() => {
            setCopyTodo(todoList);
        }, [todoList]);

        //******************************************/
        //  Functions
        //******************************************/

        const confirmed = (id, done) => {
            updateTasks(username, id, done, token).then((data) => {
                const newTodos = todoList.map((item) => {
                    if (item.id === id) {
                        item.done = done;
                    }
                    return item;
                });
                setTodoList(newTodos);
                // setCount(newTodos.filter((item) => item.done).length);
            });
        };
            
        const deleteItem = (id) => {
            deleteTasks(username, id, token).then((data) => {
                const newList = todoList.filter((item) => item.id !== id);
                setTodoList(newList);
            });
            // setCount(newList.filter((item) => item.done).length);
        };
            
        const addItem = () => {
            createTask(username, newTodoText, token).then((data) => {
                const newList = [...todoList, {id: data.id, content: data.title, done: data.done}];
                setTodoList(newList);
            });
            setNewTodoText('');
        };

        const onPressHandler = () => {
            setSubmited(true);
            if (newTodoText.length > 0) {
                addItem();
            }
            setSubmited(false);
        };
            
        const chekAll = () => {
            markTask(username, true, token).then((data) => {
                const newList = data.map((item) => ({id: item.id, content: item.title, done: item.done}));
                setTodoList(newList);
        });
        };
        
        const notCheckAll = () => {
            markTask(username, false, token).then((data) => {
                const newList = data.map((item) => ({id: item.id, content: item.title, done: item.done}));
                setTodoList(newList);
            });
        };

        const effectuees = () => {
            const newList = todoList.filter((item) => item.done);
            setCopyTodo(newList);
        };

        const nonEffectuees = () => {
            const newList = todoList.filter((item) => !item.done);
            setCopyTodo(newList);
        };
        
        // The state variable to store the focus status of the TextInput
        const [focus, setFocus] = useState(false);
       

    return (
        <View style={styles.container}>
                
                <View style={styles.disposer}>
                    <ProgressBar style={styles.progressBar}
                        progress={todoList.length > 0 ? count / todoList.length : 0} 
                />
               
               <Pressable
                    style={[styles.butto, styles.buttonOpen]}
                    onPress={() => setModalVisible(true)}>
                    <Text style={styles.textStyle}>Statistics</Text>
                </Pressable>

                </View>
                
                        <FlatList style={styles.flatlist}
                            //numColumns={1}
                            data={copyTodo}
                            renderItem={({item}) => 
                                <TodoItem _supprimer={(id) => 
                                    deleteItem(id)}  item={item} updateItem={(id, done) => 
                                        confirmed(id, done)} />}
                            keyExtractor={(item) => item.id.toString()}
                        />
               
           
                <KeyboardAvoidingView 
                    style={styles.keyboard} 
                    behavior={Platform.OS === "ios" ? "padding" : ""}
                        keyboardVerticalOffset={Platform.OS === "ios" ? 64 : 20}
                            enabled={focus}>
 
                    <TextInput style={styles.input} 
                        onChangeText={(text) => setNewTodoText(text)}
                        placeholder="Add a new task" 
                        value={newTodoText}
                           // keyboardType="default" // numeric, email-address, phone-pad, default, url, number-pad
                        maxLength={100} // number, default 524288, 0 = no limit, -1 = no limit, but don't allow new lines
                        onFocus={() => setFocus(true)}
                        onBlur={() => setFocus(false)}
                    />

                    <Boutton style={styles.button}
                        title="Add"
                        onPress={onPressHandler} 
                    />
                
           
                </KeyboardAvoidingView> 

                    <View style={styles.all}>
                        <Boutton style={styles.button}
                            title="Show all tasks"
                            onPress={() => setCopyTodo(todoList)}
                        />
                    </View>

                    <View style={styles.all}>
                        <Boutton style={styles.button} 
                            title="Tick" 
                            onPress={chekAll} 
                        />
                        <Boutton style={styles.button} 
                            title="Uncheck" 
                            onPress={notCheckAll} 
                        />
                        <Boutton style={styles.button} 
                            title="Done" 
                            onPress={effectuees} 
                        />
                        <Boutton style={styles.button} 
                            title="Not done" 
                            onPress={nonEffectuees} 
                        />

                       

                    </View>

            <View style={styles.centeredView}>
                <Modal
                    animationType="slide"
                    transparent={true}
                    visible={modalVisible}
                    onRequestClose={() => {
                    Alert.alert("Modal has been closed.");
                    setModalVisible(!modalVisible);
                    }}
                >
                    <View>
                    <View style={styles.modalView}>
                        <Text style={styles.modalText}>Number of tasks : {todoList.length} </Text>
                        <Text style={styles.modalText}>Number of tasks completed : {count} </Text>
                        <Text style={styles.modalText}>Number of tasks remaining : {todoList.length - count}</Text>
                        <Pressable
                        style={[styles.button, styles.buttonClose]}
                        onPress={() => setModalVisible(!modalVisible)}
                        >
                        <Text style={styles.buttonClose}>Close</Text>
                        </Pressable>
                    </View>
                    </View>
                </Modal>
            </View>

        </View>    
        
    )

}

//*********************************************/
//        Styles for the TodoList component 
//********************************************/

let styles = StyleSheet.create({

    container: {
        flex: 1,
        backgroundColor: 'white',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 20,
    },
    keyboard: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        width: '100%',
       
    },
    input: {
        flex: 6,
        borderColor: 'gray',
        borderWidth: 1,
        borderRadius: 5,
        padding: 10,
        marginLeft: 10,
    },
    all: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        width: '100%',
        marginTop: 10,
    },
    button: {
        flex: 1,     
    },

    flatlist: {
        width: '80%',
        marginTop: 10,
        flex: 8,
        backgroundColor: '#fff',
        height: "40%",
    },
    disposer: {

        flexDirection: "row",
        justifyContent: "center",
        padding: 15,
        width: "100%", 
     
    },
    
    ///MODAL

    modalView: {
        marginTop: "15%",
        marginRight: "10%",
        marginLeft: "10%",
        backgroundColor: "white",
        borderRadius: 10,
        borderWidth: 3,
        borderColor: "black",
        padding: 35,
        alignItems: "center",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5
      },
      butto: {
        borderRadius: 30,
        paddingVertical: 16,
        paddingHorizontal: 32,
        margin : 16,
        alignItems: "center",
        padding: 10,
        elevation: 2
      },
      buttonOpen: {
        backgroundColor: "#123456",
      },
      buttonClose: {
        color: "white",
        borderWidth: 5,
        borderRadius: 5,
        fontSize: 20,
        backgroundColor: "#123456",
      },
      textStyle: {
        color: "white",
        fontWeight: "bold",
        textAlign: "center"
      },
      modalText: {
        // borderWidth: 3,
        fontStyle: "bold",
        fontSize: 20,
        marginBottom: 15,
        textAlign: "center"
      },
    
   
});

// for small devices (width < 500px)
const small = StyleSheet.create({

    container: {
        flex: 1,
        backgroundColor: 'white',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 20,
    },

    keyboard: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        width: '100%',
       
    },
    all: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginTop: 10,
        marginRight: "5%",
        marginLeft: "5%"
    },
    button: {   
        flex: 1,
        margin: 5,
        borderRadius: 30,
        backgroundColor: "#123456",
        alignItems: "center",
        justifyContent: "center",

       
    },
    input: {
        flex: 6,
        borderColor: 'gray',
        borderWidth: 1,
        borderRadius: 5,
        padding: 10,
        marginLeft: 10,
    },
    disposer: {
        
        flexDirection: "row",
        justifyContent: "center",
        padding: 15,
        width: "100%",
        marginRight: "5%"
    },
    flatlist: {
        width: '80%',
        marginTop: 10,
        flex: 8,
        backgroundColor: '#fff',
        height: "40%",
    },

     ///MODAL

     modalView: {
        marginTop: "15%",
        marginRight: "10%",
        marginLeft: "10%",
        backgroundColor: "white",
        borderRadius: 10,
        borderWidth: 3,
        borderColor: "black",
        padding: 35,
        alignItems: "center",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5
      },
      butto: {

       borderRadius: 30,
        paddingVertical: 16,
        paddingHorizontal: 32,
        margin : 16,
        alignItems: "center",
        padding: 10,
        elevation: 2
      },
      buttonOpen: {
        backgroundColor: "#123456",
      },
      buttonClose: {
        color: "white",
        borderWidth: 5,
        borderRadius: 5,
        fontSize: 20,
        backgroundColor: "#123456",
      },
      textStyle: {
        color: "white",
        fontWeight: "bold",
        textAlign: "center"
      },
      modalText: {
        fontStyle: "bold",
        fontSize: 20,
        marginBottom: 15,
        textAlign: "center"
      },
    //   centeredView: {
    //     flex: 1,
    //     justifyContent: "center",
    //     alignItems: "center",
    //     // marginTop: "30%",
        
    //   },
   

});

if (Dimensions.get('window').width < 500) {
    styles = {...small};
}

// const styles = StyleSheet.create({
//     container: {
//         flex: 1,
//         // width: '100%',
//         // backgroundColor: "#f5f5f5",
//         alignItems: "center",
//         //justifyContent: "center",
//        // padding: 10,   
//        // position: "fixed",
//     },
//     progressBar:{
        
//         width: 400,
//         height: 20,
//         backgroundColor: "green",
//         borderRadius: 10,
//         marginLeft: '10%',
//         marginRight: '1%',
//         marginTop: 10,
//         marginBottom: "15%",
//     },

//     flatlist: {
//        // flex: 1,
//        position: "fixed",
//        backgroundColor: "ffffff", //"aquamarine",
//         borderWidth: 5,
//        borderRadius: 6,
//         marginTop: '10%',
//         //marginLeft: '10%',
//         padding: 10,
//        height: "40%",
//         width: "60%",
//     },

//     contenu: {
//         flex: 1,
        
//     },
//     text: {
//         fontSize: 20,
//         color: "green",
//         fontWeight: "bold",
//         textAlign: "center",
//         justifyContent: "center",
//         alignItems: "center",
//         marginTop: 10,
//         marginBottom: 10,
//     },
//     input: {

//         flex: 6,
//         borderWidth: 1,
//         borderColor: "#000",
//         borderRadius: 10,
//         padding: 16,

//     },
//     ajouter: {
//         width: 15,
//         height: 28,
//         color: 'purple',
//         borderRadius: 3,
//         justifyContent: 'center',
//         alignItems: 'center',
//     },

//     items: {
//         flex: 1,
//         width: '100%',
//         backgroundColor: "#f5f5f5"
//     },
//     disposer: {
//         flex: 1,
//         flexDirection: "row",
//         justifyContent: "space-between",
//         //alignItems: "center",
//         backgroundColor: "white",
//         padding: 10,
//         //borderWidth: 1,
//         borderColor: "#000",
//         borderRadius: 10,
//         marginTop: 10,
//         marginBottom: 10,
//     },
//     addItem: {
//         flex: 1,
//         flexDirection: "row",
//         justifyContent: "center",
//         alignItems: "center",
        
//     },
//     all: {
//         //flex: 1,
//         position: "fixed",
//         bottom: 35,

//         justifyContent: "space-between",
//         alignItems: "center",
//         flexDirection: "row",
//         width: "100%",
//     },
//     keyboard: {
//         flexDirection: "row",
//         alignItems: "center",
//         justifyContent: "space-between",
//         width: "100%",
//         position: "fixed",
//         marginTop: "50%",
//         bottom: 90,

//     },

    
//     checked: {
//         flex: 1,
//         flexDirection: "row",
//         justifyContent: "space-between",
//         alignItems: "center",    
        
//     },
//     ca: {
//         flex: 1,
//         margin: 10,
//     },
//     nca: {
//         flex: 1,
//         margin: 10,
//     },
//     ene:{
//         flex: 1,
//         flexDirection: "row",
//         marginBottom: 10,

//     },
//     button:{
//         flex: 1,
//         marginLeft: 10,
//     },
//     // Media query
//     "@media (max-width: 600px)": {
//         keyboard: {
//             flexDirection: "column",
//         },
//         input: {
//             flex: 1,
//             marginBottom: 16,
//         },
//     },
//     "@media (min-width: 600px) and (max-width: 800px)": {
//         keyboard: {
//             flexDirection: "column",
//         },
//         input: {
//             flex: 1,
//             marginBottom: 16,
//         },
//     },
//     "@media (min-width: 800px)": {
//         keyboard: {
//             flexDirection: "row",
//         },
//         input: {
//             flex: 6,
//             marginBottom: 0,
//         },
//     }
// });