import { View, Text } from 'react-native'
import React from 'react'
import { createStackNavigator } from '@react-navigation/stack';
import Tabs from './Tabs';
import Login from '../screen/Login';

const Stack = createStackNavigator();


const defaultOptions = {
  headerShown: false
}

const Stacks = () => {
  return (
    <Stack.Navigator screenOptions={defaultOptions} initialRouteName="Login">
      <Stack.Screen name="Login" component={Login} />
      <Stack.Screen name="Tabs" component={Tabs} />
    </Stack.Navigator>

  )
}

export default Stacks