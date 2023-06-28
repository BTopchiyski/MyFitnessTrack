import { View, Text, SafeAreaView, StyleSheet, ScrollView, Alert } from 'react-native'
import React, { useEffect, useState } from 'react'
import { isEmpty } from 'lodash'
import Input from '../components/Input'
import Separator from '../components/Separator'
import Button from '../components/Button'
import goalClient from '../client/goalClient'
import { createMissingMeasurementsPrompt } from '../utils'

export const generateStringFromMacroGoal = (g) => `${g?.goal}: ${g?.proteinGrams}g /${g?.carbohydrateGrams}g /${g?.fatGrams}g`


const MacroGoals = ({ navigation }) => {
  const [goalIntakes, setGoalIntakes] = useState([])
  const [currentGoal, setCurrentGoal] = useState();
  
  useEffect(() => {
    navigation.setOptions({ title: 'Macro goals', })
  }, [navigation]);

  useEffect(() => {
    (async () => {
      const response = await goalClient.getMacroGoals()
      if(!isEmpty(response) && !isEmpty(response[0]?.error)) {
        createMissingMeasurementsPrompt(navigation.goBack)
        return;
      }
      if(!isEmpty(response) && isEmpty(response?.error)) {
        setGoalIntakes(response)
      }
      const _currentGoal = await goalClient.getCurrentMacros();
      if(!isEmpty(_currentGoal) && isEmpty(_currentGoal.error)) {
        setCurrentGoal(_currentGoal)
      }
    })();
  }, [])

  const setGoal = (goal) => async () => {
    const newGoal = await goalClient.setMacroGoal(goal);
    if(!isEmpty(newGoal) && isEmpty(newGoal.error)) {
      setCurrentGoal(newGoal)
    }
  }

  const renderGoalButtons = () => {
    return goalIntakes.map(g => 
      <Button onPress={setGoal(g)} title={generateStringFromMacroGoal(g)} />)
  }
const value = !isEmpty(currentGoal) && isEmpty(currentGoal?.error) ? generateStringFromMacroGoal(currentGoal, '') : '0'
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.text}>Protein/Cardohydrates/Fats</Text>
      <Input multiline value={value} editable={false} />
      <Separator />
      {renderGoalButtons()}
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: { alignItems: 'center' },
  text: {
    fontSize: 24,
    color: 'white',
    fontStyle: 'italic',
    textAlign: 'center'
  }
})

export default MacroGoals