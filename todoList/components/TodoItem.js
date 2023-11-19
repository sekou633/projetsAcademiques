//**************************************/
//  Importing React Native Components
//*****************************************/

import React ,{useEffect, useState}  from "react";
import { View, Switch, StyleSheet, Image , Text, TouchableOpacity} from 'react-native';


export default function TodoItem(props){
    console.log(props);
    const [done , setDone] = useState(props.item.done);

    useEffect(() => {
        props.updateItem(props.item.id, done);
    }, [done]);

    useEffect(() => {
        setDone(props.item.done);
    }, [props.item.done]);

    return (
        <View style={[styles.container, props.style]}>

            <Switch 
                style = {styles.switch}
                onValueChange={(state) => setDone(state)}
                value ={done}
                trackColor={{ false: "#767577", true: "#81b0ff" }}
                thumbColor={done ? "rgb(0,0,0)" : "#f4f3f4"}
                ios_backgroundColor="#00ffff"
            />

            <Text style={[styles.text_item, { textDecorationLine: done ? 'line-through' : 'none' }]}>
                {props.item.content}
            </Text>
            
            <TouchableOpacity onPress={() => props._supprimer(props.item.id)}>
                <Image source={require('../assets/trash-can-outline.png')} style={styles.image} />
            </TouchableOpacity>

        </View>
    )
}

//*******************************************/
//      styles for TodoItem.js 
//*********************************************/

const styles = StyleSheet.create({
    container: {
        flexDirection:'row',
        alignItems : 'center',
        backgroundColor: "ffffff", //"aquamarine",
        borderBottomWidth: 1,
        borderRadius: 5,
        borderColor: "#ccc",
        marginBottom: 10,
        shadowOffset: {width: 0, height: 2},
        shadowColor: "black",
        shadowOpacity: 0.27,
        elevation: 5,
        padding: '2%',
        width: "100%",
        justifyContent: "space-between",

    },
    switch : {
        margin : 10,
        transform : [{scaleX : 0.8}, {scaleY : 0.8}],
    },
    button : {
        alignItems : "center",
        padding : 10,
        backgroundColor : 'green',
        color : "red",
        borderRadius : 10,
        justifyContent : "center",
    },
    text_item : {
        fontSize : 20,
        colors : 'black',
        fontWeight : 'bold',
        marginLeft : 10,
        width : 200
    },
    image : {
        width : 20,
        height : 20,
    }

});
