import { View, Text, StyleSheet, TextInput } from 'react-native'
import React from 'react'

const Input = ({ onChangeText, value, title, number = false, suffix, prefix, secure=false, editable = true, multiline = false }) => {
  return (
    <View style={styles.container}>
      <Text sacl style={styles.text}>{prefix}</Text>
      <TextInput
        style={[styles.input, styles.text, (suffix || prefix) && styles.suffixAvailable]}
        value={value?.toString()}
        scrollEnabled
        onChangeText={onChangeText}
        placeholderTextColor="white"
        placeholder={title}
        inputMode={number ? "decimal" : undefined}
        keyboardType={number ? "decimal-pad" : undefined}
        secureTextEntry={secure}
        editable={editable}
        multiline={multiline}
      />
      <Text style={styles.text}>{suffix}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  suffixAvailable: {maxWidth: '80%'  },
  container: {
    backgroundColor: 'rgb(173,227,226)',
    borderColor: 'white',
    borderWidth: 2,
    borderRadius: 32,
    margin: 32,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 10,
    padding: 32,
    flexDirection: 'row'
  },
  text: {
    color: 'white',
    textShadowColor: 'black',
    fontSize: 32,
    fontStyle: 'italic',
    paddingHorizontal: 4
  },
  input: {
    textAlign: 'center',
    overflow: 'scroll',
    flex: 1,
  }
})
export default Input