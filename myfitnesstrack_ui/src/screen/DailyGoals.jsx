import { View, Text, SafeAreaView, StyleSheet, ScrollView } from 'react-native'
import React, { useEffect, useState } from 'react'
import authClient from '../client/authClient'
import { isEmpty } from 'lodash'
import Input from '../components/Input'
import Button from '../components/Button'
import goalClient from '../client/goalClient'


const DailyGoals = ({ navigation }) => {
  const [currentMacroGoal, setCurrentMacroGoal] = useState({ proteinGrams: 0, carbohydrateGrams: 0, fatGrams: 0 });
  const [currentCalorieGoal, setCurrentCalorieGoal] = useState({ calorie_goal: 0 });
  

  useEffect(() => {
    navigation.setOptions({ title: 'Daily goals', })
  }, [navigation]);

  useEffect(() => {
    (async () => {
      const _currentCalorieGoals = await goalClient.getCurrent();
      if(!isEmpty(_currentCalorieGoals) && isEmpty(_currentCalorieGoals.error)) {
        setCurrentCalorieGoal(_currentCalorieGoals)
      }
      const _currentMacroGoal = await goalClient.getCurrentMacros();
      if(!isEmpty(_currentMacroGoal) && isEmpty(_currentMacroGoal.error)) {
        setCurrentMacroGoal(_currentMacroGoal)
      }
    })()
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <Input prefix={"Calories:"} suffix="kcal" value={currentCalorieGoal.calorie_goal?.toString()} editable={false} />
      <Input value={`Macros: ${currentMacroGoal?.proteinGrams}g /${currentMacroGoal?.carbohydrateGrams}g /${currentMacroGoal?.fatGrams}g`} editable={false} multiline />
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center' },
  text: {
    fontSize: 32,
    color: 'white',
    fontWeight: 'bold',
    textAlign: 'center'
  }
})

export default DailyGoals