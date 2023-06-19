import { View, Text, Button, SafeAreaView, TextInput, StyleSheet } from 'react-native'
import React from 'react'

const Login = ({ navigation }) => {
  const onPress = () => {
    navigation.reset({ index: 0, routes: [{ name: 'Tabs' }] })
  }
  return (
    <SafeAreaView style={styles.container}>
      <TextInput style={styles.input} placeholder='Email' placeholderTextColor={'black'} inputMode='email'/>
      <TextInput style={styles.input} placeholder='Password' placeholderTextColor={'black'} secureTextEntry/>
      <Button onPress={onPress} title='Login' />
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center' },
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
    color: 'black',
    borderRadius: 8
  }
})

export default Login