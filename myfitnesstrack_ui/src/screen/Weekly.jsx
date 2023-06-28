import { View, Text, StyleSheet } from 'react-native'
import React, { useCallback, useEffect, useMemo, useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import SimpleButton from '../components/SimpleButton'
import { useFocusEffect } from '@react-navigation/native'
import Input from '../components/Input'
import Separator from '../components/Separator'
import progressClient from '../client/progressClient'
import { createMissingProgressEntriesPrompt } from '../utils'
import { isEmpty } from 'lodash'



const Weekly = ({ navigation }) => {
  const [calories, setCalories] = useState()
  const [weight, setWeight] = useState()

  useFocusEffect(
    useCallback(() => {
      (async () => {
        const response = await progressClient.getWeeklyEntry()
        if (!isEmpty(response) && !isEmpty(response?.error)) {
          createMissingProgressEntriesPrompt(navigation.goBack)
          return;
        }
        if (isEmpty(response.error) && response?.averageCaloriesTaken !== null){
          setCalories(response.averageCaloriesTaken)
        }
        if (isEmpty(response.error) && response?.averageWeight !== null){
          setWeight(response?.averageWeight)
        }
      })()
    }, [navigation])
  );

  return (
    <SafeAreaView style={styles.container}>
      <View style={{ flexDirection: 'row', justifyContent: 'space-between' }}>
        <SimpleButton title={'Calories'} disabled />
        <SimpleButton title={'Weight'} disabled />
        <SimpleButton title={'Resume'} />
      </View>
      <Text style={styles.text}>Congratulations, this week you have lost approximately: </Text>
      <Input value={weight} suffix="KG" editable={false} />
      <Separator />
      <Text style={styles.text}>You are also ahead of the average calories game with:</Text>
      <Input value={calories} suffix="kcal" editable={false} />
    </SafeAreaView>
  )
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center'
  },
  text: {
    fontSize: 20,
    color: 'white',
    textAlign: 'center',
  }
})

export default Weekly