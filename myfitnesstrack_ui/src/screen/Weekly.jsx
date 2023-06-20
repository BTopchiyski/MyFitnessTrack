import { View, Text, StyleSheet } from 'react-native'
import React, { useCallback, useMemo, useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import SimpleButton from '../components/SimpleButton'
import { useFocusEffect } from '@react-navigation/native'
import Input from '../components/Input'
import Separator from '../components/Separator'


const Weekly = () => {

  return (
    <SafeAreaView style={styles.container}>
      <View style={{ flexDirection: 'row', justifyContent: 'space-between' }}>
        <SimpleButton title={'Calories'} disabled />
        <SimpleButton title={'Weight'} disabled />
        <SimpleButton title={'Resume'} />
      </View>
      <Text style={styles.text}>Congratulations, this week you have lost approximately: </Text>
      <Input value="0.7" suffix="KG" editable={false} />
      <Separator />
      <Text style={styles.text}>You are also ahead of the average calories game with:</Text>
      <Input value="240" suffix="kcal" editable={false} />
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
  }
})

export default Weekly