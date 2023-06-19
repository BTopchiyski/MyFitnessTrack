import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Dashboard from '../screen/Dashboard';
import Weight from '../screen/Weight';
import Weekly from '../screen/Weekly';
import Monthly from '../screen/Monthly';

const Tab = createBottomTabNavigator();

const styles = StyleSheet.create({
    header: {
    }
})

const Tabs = () => {
  return (
    <Tab.Navigator screenOptions={{ headerStyle: styles.header }}>
      <Tab.Screen options={{ title: 'Welcome back!' }} name="Dashboard" component={Dashboard} />
      <Tab.Screen name="Weight" component={Weight} />
      <Tab.Screen name="Weekly" component={Weekly} />
      <Tab.Screen name="Monthly" component={Monthly} />
    </Tab.Navigator>

  )
}

export default Tabs