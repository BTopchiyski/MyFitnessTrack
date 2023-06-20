import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Dashboard from '../screen/Dashboard';
import Weight from '../screen/Weight';
import Weekly from '../screen/Weekly';
import Monthly from '../screen/Monthly';
import Profile from '../screen/Profile';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';


const headerStyle = {
  header: {
    backgroundColor: 'transparent',
    height: 100,
  },
  headerTitle: {
    fontSize: 32,
    paddingBottom: 32,
    borderBottomColor: 'white',
    borderBottomWidth: 2,
  },
}

const Tab = createBottomTabNavigator();

const styles = StyleSheet.create({
  ...headerStyle,
  tabBar: {
    alignSelf: 'center',
    alignItems: 'center',
    backgroundColor: 'rgb(115,227,194)',
    height: 80,
    borderRadius: 32,
    marginBottom: 10,
    marginHorizontal: 6,
    paddingBottom: 10,
    elevation: 10,
    borderTopColor: 'white',
    borderTopWidth: 2,
    borderLeftColor: 'white',
    borderLeftWidth: 2,
    borderRightColor: 'white',
    borderRightWidth: 2
  },
  scene: {
    backgroundColor: 'rgb(153,210,209)'
  }
})

export const headerOptions = {
  headerShown: true,
  headerStyle: styles.header,
  headerTitleStyle: styles.headerTitle,
  headerTitleAlign: 'center',
  headerTintColor: 'white',
  headerShadowVisible: false,
}


const Tabs = () => {
  return (
    <Tab.Navigator screenOptions={{
      ...headerOptions,
      tabBarActiveTintColor: 'white',
      tabBarStyle: styles.tabBar
    }} sceneContainerStyle={styles.scene}>
      <Tab.Screen options={{
        tabBarLabel: 'Dashboard',
        tabBarIcon: (props) => <MaterialIcons name="dashboard"  {...props} />
      }}
        name="Dashboard"
        component={Dashboard}
      />
      <Tab.Screen name="Weight" options={{
        tabBarLabel: 'Weight',
        tabBarIcon: (props) => <FontAwesome5 name="weight" {...props} />
      }} component={Weight} />
      <Tab.Screen name="Weekly" options={{
        tabBarLabel: 'Weekly',
        tabBarIcon: (props) => <MaterialCommunityIcons name="calendar-week" {...props} />
      }} component={Weekly} />
      <Tab.Screen name="Monthly" options={{
        tabBarLabel: 'Monthly',
        tabBarIcon: (props) => <MaterialCommunityIcons name="calendar-month" {...props} />
      }} component={Monthly} />
      <Tab.Screen name="Profile" options={{
        tabBarLabel: 'Profile',
        tabBarIcon: (props) => <FontAwesome5 name="user" {...props} />
      }} component={Profile} />
    </Tab.Navigator>

  )
}

export default Tabs