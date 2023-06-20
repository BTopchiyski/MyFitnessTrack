import { View, Text, StyleSheet, TouchableOpacity } from 'react-native'
import React from 'react'

const SimpleButton = ({ onPress, title, disabled = false }) => {
  return (
    <TouchableOpacity style={[styles.container, disabled && styles.disabled]} onPress={onPress} disabled={disabled}>
      <Text style={styles.text}>{title}</Text>
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
  disabled: {
    opacity: 0.5
  },
  container: {
    minWidth: 100,
    backgroundColor: 'rgb(194,244,244)',
    borderColor: 'white',
    borderWidth: 2,
    borderRadius: 18,
    margin: 8,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 10,
    padding: 18,
    paddingVertical: 24,
  },
  text: {
    color: 'white',
    textShadowColor: 'black',
    fontSize: 16,
  }
})
export default SimpleButton