import { View, Text, StyleSheet, TouchableOpacity } from 'react-native'
import React from 'react'

const Button = ({ onPress, title, disabled = false }) => {
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
    backgroundColor: 'rgb(194,244,244)',
    borderColor: 'white',
    borderWidth: 2,
    borderRadius: 64,
    margin: 32,
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'row',
    elevation: 10,
    padding: 24,
    paddingVertical: 32,
  },
  text: {
    color: 'white',
    textShadowColor: 'black',
    fontSize: 24,
    fontWeight: 'bold',
    fontStyle: 'italic',
    textTransform: 'uppercase',
    textAlign: 'center',
    flex: 1,
  }
})
export default Button