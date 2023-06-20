import { View, Text } from 'react-native'
import React from 'react'
import { CardStyleInterpolators, createStackNavigator } from '@react-navigation/stack';
import Tabs, { headerOptions, headerStyle } from './Tabs';
import Login from '../screen/Login';
import Launcher from '../screen/Launcher';
import Measurements from '../screen/Measurements';
import DailyGoals from '../screen/DailyGoals';
import CalorieGoals from '../screen/CalorieGoals';
import MacroGoals from '../screen/MacroGoals';

const Stack = createStackNavigator();


const defaultOptions = {
  headerShown: false,
  cardStyle: { backgroundColor: 'rgb(153,210,209)' }
}

const Stacks = () => {
  return (
    <Stack.Navigator screenOptions={{ ...defaultOptions }} initialRouteName="Launcher">
      <Stack.Screen name="Launcher" component={Launcher} />
      <Stack.Screen name="Login" component={Login} />

      <Stack.Screen name="Tabs" component={Tabs} />
      <Stack.Group screenOptions={{ ...headerOptions, presentation: 'modal', cardStyleInterpolator: CardStyleInterpolators.forHorizontalIOS }}>
        <Stack.Screen name="Measurements" component={Measurements} />
        <Stack.Screen name="DailyGoals" component={DailyGoals} />
        <Stack.Screen name="CalorieGoals" component={CalorieGoals} />
        <Stack.Screen name="MacroGoals" component={MacroGoals} />

      </Stack.Group>
    </Stack.Navigator>

  )
}

export default Stacks