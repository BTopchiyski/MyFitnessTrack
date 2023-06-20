import { View, Text, SafeAreaView, StyleSheet, ScrollView, Alert } from 'react-native'
import React, { useEffect, useState } from 'react'
import { isEmpty } from 'lodash'
import Input from '../components/Input'
import Separator from '../components/Separator'
import Button from '../components/Button'
import goalClient from '../client/goalClient'
import { createMissingMeasurementsPrompt } from '../utils'


const CalorieGoals = ({ navigation }) => {
  const [goalIntakes, setGoalsIntakes] = useState({
    maintain: { calorie_goal: 0 },
    weightLoss: { calorie_goal: 0 },
    gain: { calorie_goal: 0 }
  })
  const [currentGoal, setCurrentGoal] = useState({ calorie_goal: 0 });
  useEffect(() => {
    navigation.setOptions({ title: 'Calorie goals', })
  }, [navigation]);

  useEffect(() => {
    (async () => {
      const maintain = await goalClient.getMaintain();
      const weightLoss = await goalClient.getWeightloss();
      const gain = await goalClient.getGain();
      if(maintain?.error || weightLoss?.error || gain?.error) {
        createMissingMeasurementsPrompt(navigation.goBack)
        return;
      }
      setGoalsIntakes({ maintain, weightLoss, gain });
      const _currentGoal = await goalClient.getCurrent();
      if(!isEmpty(_currentGoal) && isEmpty(_currentGoal.error)) {
        setCurrentGoal(_currentGoal)
      }
    })();
  }, [])

  const setGoal = (stateGoal) => async () => {
    const newGoal = await goalClient.setCurrent(stateGoal.calorie_goal);
    if(!isEmpty(newGoal) && isEmpty(newGoal.error)) {
      setCurrentGoal(newGoal)
    }
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Input prefix={"Current:"} suffix="kcal" value={currentGoal.calorie_goal.toString()} editable={false} />
      <Separator />
      <Button onPress={setGoal(goalIntakes.maintain)} title={`Maintain: ${goalIntakes.maintain.calorie_goal}`} />
      <Button onPress={setGoal(goalIntakes.weightLoss)} title={`Weight loss: ${goalIntakes.weightLoss.calorie_goal}`} />
      <Button onPress={setGoal(goalIntakes.gain)} title={`Gain: ${goalIntakes.gain.calorie_goal}`} />
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', paddingVertical: 32 },
  text: {
    fontSize: 32,
    color: 'white',
    fontWeight: 'bold',
    textAlign: 'center'
  }
})

export default CalorieGoals