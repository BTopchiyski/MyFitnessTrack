import { Alert, SafeAreaView, ScrollView, StyleSheet, Text, View } from 'react-native'
import React, { useCallback, useEffect, useState } from 'react'
import Button from '../components/Button'
import authClient from '../client/authClient'
import Separator from '../components/Separator'
import { useFocusEffect } from '@react-navigation/native'
import { isEmpty } from 'lodash'



const Profile = ({ navigation }) => {
  const [currentUser, setCurrentUser] = useState();
  const logout = async () => {
    Alert.alert(
      'Logout',
      'Are you sure you want to logout',
      [
        {
          text: 'Cancel',
          style: 'cancel',
        },
        {
          text: 'Yes',
          onPress: async () => {
            await authClient.logout();
            navigation.reset({ index: 0, routes: [{ name: 'Login' }] })
          },
          style: 'default',
        },
      ],
    )
  }

  useFocusEffect(
    useCallback(() => {
      (async () => {

        const result = await authClient.check();
        if (!isEmpty(result)) {
          setCurrentUser(result)
        }
      })()
    }, [])
  );

  const goToMeasurements = () => {
    navigation.push('Measurements')
  }

  const goToCalorieGoals = () => {
    navigation.push('CalorieGoals')
  }

  const goToMacroGoals = () => {
    navigation.push('MacroGoals')
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <View>
        <Text style={styles.title}>{currentUser?.firstName} {currentUser?.lastName}</Text>
        <Text style={styles.title}>{currentUser?.email}</Text>
      </View>
      <Separator />
      <Button onPress={goToMeasurements} title="Measurements" />
      <Button onPress={goToCalorieGoals} title="Calorie goals" />
      <Button onPress={goToMacroGoals} title="Marconutrients goals" />
      <Button onPress={logout} title="Log out" />
    </ScrollView>
  )
}


const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
  },
  row: {
    flexDirection: 'row'
  },
  title: {
    fontSize: 20,
    color: 'white',
    textAlign: 'center',
  }
})

export default Profile