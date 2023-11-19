//**************************************/
//  Importing React Native Components
//*****************************************/

import React from 'react';
import { StyleSheet, View, Text, Dimensions } from 'react-native';

//*************************************************/
// ProgressBar component that can be used in any screen
//*************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {props} props 
 * @returns View component with a progress bar and a text 
 */


export default function ProgressBar(props={progress: 0}) {

    const percentage = props.progress * 100;

    return (
        <View style={styles.container}>
                <Text style={styles.progressText}>Percentage of tasks completed : {percentage.toFixed(0)}%</Text>
                <View style={styles.progressBar}>
                    <View style={[styles.progress, { width: `${percentage}%` }, {backgroundColor: `${percentage}` < 50 ? 'red' : 'green'}]} />
                </View>
        </View>
    );
};

//***********************************************************/
//          Style for a responsive progress bar 
//************************************************************/

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
        width: "80%",
    },
    progressText: {
        textAlign: "center",
        justifyContent: "center",
        fontSize: 20,
        margin : 10,
        fontWeight: "bold",
        
    },
    progressBar: {
        width: "70%",
        height: 30,
        backgroundColor: "#ddd",
        borderRadius: 10,
        overflow: "hidden",
        marginLeft: '10%',
        marginRight: '8%',
       
    },
    progress: {
        height: "100%",
        backgroundColor: "#008080",
    },
   
});
