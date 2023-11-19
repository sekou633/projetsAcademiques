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

import { signIn } from '../API/todoAPI'

import { TokenContext } from '../Context/Context'
import { UsernameContext } from '../Context/Context'

export default function SignIn () {
  
  const [login, setLogin] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [visible, setVisible] = useState(true)

  const getSignedIn = (setToken, setUsername) => {
    setError('')
    if (login == '' || password == '') return
    setVisible(false)
    signIn(login, password)
      .then(token => {
        setUsername(login)
        setToken(token)
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
                      <Text style={style.title_text}>CONNECTION</Text>
                      
                    </View>
                    
                    <View style={style.input_group}>
                      <Text style={style.text}>Login</Text>
                      <View style={style.input_container}>
                      <TextInput
                        
                        onChangeText={setLogin}
                        onSubmitEditing={() =>
                          getSignedIn(setToken, setUsername)
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
                          getSignedIn(setToken, setUsername)
                        }
                        value={password}
                      />
                      </View>
                    </View>
                    <Button style={[style.button, style.button_text]}
                      onPress={() => getSignedIn(setToken, setUsername)}
                      title='Sign In'
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
  }

})
