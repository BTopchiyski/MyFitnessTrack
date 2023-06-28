import { View, Text, StyleSheet } from 'react-native'
import React, { useCallback, useMemo, useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import SimpleButton from '../components/SimpleButton'
import { useFocusEffect } from '@react-navigation/native'
import Input from '../components/Input'
import Separator from '../components/Separator'
import progressClient from '../client/progressClient'
import { isEmpty } from 'lodash'

const randomNumber = (min, max) => {
  return Math.floor(Math.random() * (max - min + 1) + min)
}


const MOTIVATIONAL_QUOTES = [
  'Just one small positive thought in the morning can change your whole day. — Dalai Lama',
  "Opportunities dont happen, you create them. — Chris Grosser",
  'Love your family, work super hard, live your passion. — Gary Vaynerchuk',
  "It is never too late to be what you might have been. — George Eliot",
  "Don't let someone else's opinion of you become your reality — Les Brown",
  "If you’re not positive energy, you’re negative energy. Mark Cuban",
  "I am not a product of my circumstances. I am a product of my decisions. — Stephen R. Covey",
  "Do the best you can. No one can do more than that. ―John Wooden",
  "If you can dream it, you can do it. ―Walt Disney",
  "Do what you can, with what you have, where you are",
]


const Monthly = (navigation) => {
  const [motivationQuote, setQuote] = useState('');
  const [calories, setCalories] = useState()
  const [weight, setWeight] = useState()

  //set motivation quotes
  useFocusEffect(
    useCallback(() => {
      const newIndex = randomNumber(0, MOTIVATIONAL_QUOTES.length - 1);
      setQuote(MOTIVATIONAL_QUOTES[newIndex])
    }, [navigation])
  );
//set weight and calories
  useFocusEffect(
    useCallback(() => {
      (async () => {
        const response = await progressClient.getMonthlyEntry()
        console.log(response)
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
      <Text style={styles.text}>This month you've lost: </Text>
      <Input value={weight} suffix="KG" editable={false} />
      <Separator />
      <Text style={styles.text}>Your average calories are:</Text>
      <Input value={calories} suffix="kcal" editable={false} />
      <Separator />
      <Text style={[styles.text, styles.italic]}>"{motivationQuote}"</Text>
    </SafeAreaView>
  )
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems:'center'
  },
  text: {
    fontSize: 20,
    color: 'white',
    textAlign: 'center',
    padding: 8,
  },
  italic: {
    fontStyle: 'italic'
  }
})

export default Monthly