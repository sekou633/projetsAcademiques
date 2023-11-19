//**************************************/
//  Importing React Native Components
//*****************************************/

import React from 'react';
import { StyleSheet, View, TouchableOpacity, Text} from 'react-native';

//*************************************************/
// Button component that can be used in any screen
//*************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {props} props 
 * @returns TouchableOpacity component with a rounded border and a text 
 */

export default function Boutton(props) {
    
    return (
        <TouchableOpacity
            style={[styles.button, {
                backgroundColor: props.color ? props.color : "#123456",
                borderColor: props.color ? props.color : "#123456",
            }, props.style]}
            onPress={props.onPress}>
            <Text style={styles.text}>{props.title}</Text>
        </TouchableOpacity>
    );
}

//***********************************************************/
// Style for a responsive button that have a rounded border
//************************************************************/

const styles = StyleSheet.create({
    button: {
        borderRadius: 30,
        paddingVertical: 16,
        paddingHorizontal: 32,
        margin: 16,
        alignItems: "center",
    },
    text: {
        color: "#fff",
        textAlign: "center",
    },
});

Boutton.defaultProps = {
    text: "Button",
    onPress: () => {},
    styleButton: styles.button,
    styleText: styles.text,
};
