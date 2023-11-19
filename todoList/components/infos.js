//**************************************/
//  Importing React Native Components
//*****************************************/

import React, {useState}  from "react";

import { View, Text } from 'react-native'

import TodoList from "./TodoList";

import todoData from "../Helpers/todoDatas";


export default function Infos(props) {

    const [todoList, setTodoList] = useState(todoData)
    const [copyTodo, setCopyTodo] = useState(todoList)
    const [count, setCount] = useState(todoList.filter((item) => item.done).length)
        

    return (
        <View>
            <Text>Nombre de tâches : {todoList.length}</Text>
            <Text>Nombre de tâches terminées : {count}</Text>
            <Text>Nombre de tâches restantes : {todoList.length - count}</Text>
        </View>
    )
}