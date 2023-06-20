import React, { useEffect } from 'react'
import { View, SafeAreaView } from 'react-native'
import Button from '../components/Button'

const Dashboard = ({ navigation } ) => {
  useEffect(() => {
    navigation.setOptions({ title: 'Welcome back!', })
  }, []);
  return (
    <SafeAreaView>
      <Button onPress={() => navigation.push('DailyGoals')} title="Daily goals" />
      <Button title="Meal assist" disabled />
      <Button title="Fasting assist" disabled />
    </SafeAreaView>
  )
}

export default Dashboard