import { View, Text } from 'react-native'
import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import Tabs from './Tabs'
import Stacks from './Stacks'

const Navigation = ({}) => {
  return (
    <NavigationContainer>
      <Stacks />
    </NavigationContainer>
  )
}

export default Navigation