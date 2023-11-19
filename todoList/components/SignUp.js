//**************************************/
//  Importing React Native Components
//*****************************************/

import React, { useState } from 'react'
import {
  Text,
  TextInput,
  Button,
  View,
  StyleSheet,
  ActivityIndicator
} from 'react-native'

import { signUp } from '../API/todoAPI'

import { TokenContext } from '../Context/Context'
import { UsernameContext } from '../Context/Context'


export default function SignUp () {
  const [login, setLogin] = useState('')
  const [password, setPassword] = useState('')
  const [copyPassword, setCopyPassword] = useState('')
  const [error, setError] = useState('')
  const [visible, setVisible] = useState(true)

  const getSignedUp = (setToken, setUsername) => {
    setError('')
    if (login == '' || password == '' || copyPassword == '') return
    if (password != copyPassword){
        setError("Passwords don't match")
        return
    } 
    setVisible(false)
    signUp(login, password)
      .then(token => {
        setUsername(login)
        setToken(token)
        console.log('token', token)
      })
      .catch(err => {
        setError(err.message)
      })
    setVisible(true)
  }

  return (
    <TokenContext.Consumer>
      {([token, setToken]) => (
        <UsernameContext.Consumer>
          {([username, setUsername]) => {
            return (
              <View style={style.container}>
                {visible ? (
                  <>
                    <View style={style.title}>
                    <Text style={[style.title_text, style.title_colored]}> TASK MANAGEMENT  </Text>
                      <Text style={style.title_text}>INSCRIPTION</Text>
    
                    </View>
                    <View style={style.input_group}>
                      <Text style={style.text}>Login</Text>
                      <View style={style.input_container}>
                        <TextInput
                          
                          onChangeText={setLogin}
                          onSubmitEditing={() =>
                            getSignedUp(setToken, setUsername)
                          }
                          value={login}
                        />
                      </View>
                    </View>
                    <View style={style.input_group}>
                      <Text style={style.text}>Password</Text>
                      <View style={style.input_container}>
                        <TextInput
                          
                          onChangeText={setPassword}
                          secureTextEntry={true}
                          onSubmitEditing={() =>
                            getSignedUp(setToken, setUsername)
                          }
                          value={password}
                        />
                      </View>
                    </View>
                    <View style={style.input_group}>
                      <Text style={style.text}>Password Again</Text>
                      <View style={style.input_container}>
                        <TextInput
                          
                          onChangeText={setCopyPassword}
                          secureTextEntry={true}
                          onSubmitEditing={() =>
                            getSignedUp(setToken, setUsername)
                          }
                          value={copyPassword}
                        />
                      </View>
                    </View>
                    <Button
                      style={style.button}
                      onPress={() => getSignedUp(setToken, setUsername)}
                      title='Sign Up'
                    />
                    {error ? (
                      <Text style={style.text_error}>{error}</Text>
                    ) : (
                      []
                    )}
                  </>
                ) : (
                  <ActivityIndicator />
                )}
              </View>
            )
          }}
        </UsernameContext.Consumer>
      )}
    </TokenContext.Consumer>
  )
}


const style = StyleSheet.create({
  container: {
      flex:1,
      justifyContent: 'center',
      alignItems: 'center',
      // paddingHorizontal: layouts.paddingHorizontal,
      // paddingVertical: layouts.paddingVertical,
      // //backgroundColor: layouts.bgColor,
      // paddingLeft: layouts.paddingLeft,
      // paddingRight: layouts.paddingRight

  },

  text_error: {
    color: 'red'
  },

  input_container: {
      borderWidth: 1,
      borderColor: "blue",
      paddingHorizontal: 10,
      paddingVertical: 10,
      backgroundColor: '#fff',
      marginTop: 10,
      marginBottom: 10,
      borderRadius: 5

  },

  input: {
      padding: 0,
  },

  text: {
      fontSize: 16,
  },

  button: {
      backgroundColor: "blue",
      padding: 15,
      borderRadius: 5
  },

  button_text: {
      color: 'white',
      textAlign: 'center'
  },

  title: {
      flexDirection: 'row',
      margin: "2% auto"
  },

  title_text: {
      fontSize: 28,
      textAlign: 'center',
      marginBottom: 50,
      fontWeight: 'bold'
  },

  title_colored: {
      color: "blue",
      fontWeight: 'bold'
  },

  input_group : {
      marginBottom: 10
  },

  '@media screen and (max-width: 768px)': {
    container: {
      paddingLeft: 20,
      paddingRight: 20
    }
  }




})