import { View, Text, SafeAreaView, StyleSheet } from 'react-native'
import React, { useEffect, useState } from 'react'
import authClient from '../client/authClient'
import { isEmpty } from 'lodash'
import Input from '../components/Input'
import Button from '../components/Button'


const Login = ({ navigation }) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const onPress = async () => {
    const response = await authClient.login(email, password)
    if (!isEmpty(response) && isEmpty(response?.error)) {
      navigation.reset({ index: 0, routes: [{ name: 'Tabs' }] })
    }
  }

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.text}>Login</Text>
      <Input title="Email" onChangeText={setEmail} />
      <Input title="Password" onChangeText={setPassword} secure />
      <Button title="Login" onPress={onPress} />

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

export default Login