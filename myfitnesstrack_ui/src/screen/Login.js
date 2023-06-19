import { View, Text, Button, SafeAreaView } from 'react-native'
import React from 'react'

const Login = ({ navigation }) => {
  const onPress = () => {
    navigation.reset({ index: 0, routes: [{ name: 'Tabs' }] })
  }
  return (
    <SafeAreaView style={{ flex: 1, justifyContent: 'center' }}>
      <Button onPress={onPress} title='Login' />
    </SafeAreaView>
  )
}

export default Login