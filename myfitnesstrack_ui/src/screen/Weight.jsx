import { View, Text, SafeAreaView } from 'react-native'
import React, { useEffect, useState } from 'react'
import Input from '../components/Input';
import Button from '../components/Button';
import progressClient from '../client/progressClient';
import { isEmpty } from 'lodash'
import { createAlert } from '../utils';



const Weight = ({ navigation }) => {
  const [weight, setWeight] = useState(0)
  const [calories, setCalories] = useState(0)

  useEffect(() => {
    navigation.setOptions({ title: 'Weight tracker', })
  }, []);

  const onPress = async () => {
    const response = await progressClient.setEntry(weight, calories);
    if(!isEmpty(response) && !isEmpty(response?.error)) {
      createAlert('Error', response.error)
    }
  }

  return (
    <SafeAreaView>
      <Input prefix={"Weight:"} number value={weight} onChangeText={setWeight} suffix={'kg'} />
      <Input prefix={"Calories:"} number value={calories} onChangeText={setCalories} suffix={'kcal'} />

      <Button onPress={onPress} title="Register daily entry" />

    </SafeAreaView>
  )
}

export default Weight