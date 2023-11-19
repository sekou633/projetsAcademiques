// npm i --save @react-navigation/bottom-tabs @react-navigation/native 

import React, { useEffect } from 'react'
import { View, Text, Image, StyleSheet} from 'react-native'
import { NavigationContainer } from '@react-navigation/native'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'
import { createStackNavigator } from '@react-navigation/stack'

import TodoListsScreen from '../Screen/TodoListsScreen'
import HomeScreen from '../Screen/HomeScreen'
import SignInScreen from '../Screen/SignInScreen'
import SignUpScreen from '../Screen/SignUpScreen'
import SignOutScreen from '../Screen/SignOutScreen'
import App from '../App'

import { TokenContext } from '../Context/Context'
import WelcomeScreen from '../Screen/WelcomeScreen'
import UsersScreen from '../Screen/UsersScreen'

const Auth = createBottomTabNavigator()

const authScreens = () => {
  return (
    <Auth.Navigator>
      <Auth.Screen name='SignIn' component={SignInScreen} 
         options={{
          tabBarIcon: () => (<Image source={require("../assets/signIN24.png")} style={{ width: 20, height: 20 }} />),
      }}
      />
      <Auth.Screen name='SignUp' component={SignUpScreen} 
         options={{
          tabBarIcon: () => (<Image source={require("../assets/signup-icon.webp")} style={{ width: 20, height: 20 }} />),
      }}
      />
    </Auth.Navigator>
  )
}

const OpenApp = createStackNavigator()

const openAppScreens = (props) => {
  useEffect(() => {
    props.navigation.setOptions({
      headerShown: false,
      tabBarVisible: false
    })
  }, [])
  return (
    <OpenApp.Navigator>
      <OpenApp.Screen name='Welcome' component={WelcomeScreen} />
      <OpenApp.Screen name='Auth' component={authScreens} />
    </OpenApp.Navigator>
  )
}

const Tab = createBottomTabNavigator()

export default function Navigation () {
  return (
    <TokenContext.Consumer>
      {([token, setToken]) => (
        <NavigationContainer style={{zIndex: 1 , position:"fixed"}}>
          {token == null ? (
            <Tab.Navigator>
              <Tab.Screen name='Welcome' component={openAppScreens} 
                options={{
                  tabBarIcon: () => (<Image source={require("../assets/icons8-welcome-64.png")} style={{ width: 20, height: 20 }} />),
              }}
              />
            </Tab.Navigator>
          ) : (
            <Tab.Navigator>
              <Tab.Screen name='Home' component={HomeScreen} 
              options={{
                tabBarIcon: () => (<Image source={require("../assets/home.png")} style={{ width: 20, height: 20 }} />),
            }}
              />
                
              <Tab.Screen name='TodoLists' component={TodoListsScreen} 
                options={{
                  tabBarIcon: () => (<Image source={require("../assets/task1.jpg")} style={{ width: 20, height: 20 }} />),
              }}
              />
              <Tab.Screen name='SignOut' component={SignOutScreen} 
                options={{
                  tabBarIcon: () => (<Image source={require("../assets/signout.jpg")} style={{ width: 20, height: 20 }} />),
              }}
              />
              <Tab.Screen name='Users' component={UsersScreen} 
                 options={{
                  tabBarIcon: () => (<Image source={require("../assets/users.png")} style={{ width: 20, height: 20 }} />),
              }}
              />
            </Tab.Navigator>
          )}
        </NavigationContainer>
      )}
    </TokenContext.Consumer>
  )
}
