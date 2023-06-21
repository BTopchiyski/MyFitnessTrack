import { View, Text, SafeAreaView, StyleSheet } from 'react-native'
import React, { useCallback, useEffect, useState } from 'react'
import authClient from '../client/authClient'
import { isEmpty, uniqueId } from 'lodash'
import Input from '../components/Input'
import Button from '../components/Button'
import DropDownPicker from 'react-native-dropdown-picker';
import { ScrollView } from 'react-native-gesture-handler'
import activityClient from '../client/activityClient'
import { useFocusEffect } from '@react-navigation/native'



const Measurements = ({ navigation }) => {
  // const [weight, setWeight] = useState()
  // const [age, setAge] = useState()
  // const [waist, setWaist] = useState()
  // const [neck, setNeck] = useState()
  // const [wrist, setWrist] = useState()
  // const [forearm, setForearm] = useState()
  // const [hip, setHip] = useState()
  // const [height, setHeight] = useState()
  const [measurements, setMeasurements] = useState({
    weightInKilograms: 0,
    waistCircumference: 0, 
    hipCircumference: 0,
    forearmCircumference: 0,
    wristCircumference: 0, 
    neckCircumference: 0,
    age: 0,
    height: 0
  })

  // const [activityLevels, setActivityLevel] = useState([]);
  const [open, setOpen] = useState(false);
  const [activityLevel, setActivityLevel] = useState(null);
  const [items, setItems] = useState([
  ]);

  useEffect(() => {
    navigation.setOptions({ title: 'Measurements', })
  }, []);

  useFocusEffect(
    useCallback(() => {
      (async () => {
        const levels = await activityClient.getActivityLevel();
        if(!isEmpty(levels)) {
          const activities = levels.map(e => ({ label: e.activityLevel, value: e.activityLevel, key: uniqueId() }))
          setItems(activities)
          setActivityLevel(activities[0].value)
        }
        const currentMeasurement = await activityClient.getMeasurements();
        if(!isEmpty(currentMeasurement) && isEmpty(currentMeasurement?.error)) {
          setMeasurements(currentMeasurement)
        }
      })()
    }, [])
  );
  const onSave = async () => {
    console.log(await activityClient.setMeasurements({...measurements, activityLevel}))
  }

  const setMeasurement = (measurement) => (text) => setMeasurements((current) => ({ ...current, [measurement]: text }))

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView nestedScrollEnabled={true}>
        <Input prefix="Weight:" number suffix="kg" value={measurements.weightInKilograms} onChangeText={setMeasurement('weightInKilograms')} />
        <Input prefix="Height:" number suffix="cm" value={measurements.height} onChangeText={setMeasurement('height')} />
        <Input prefix="Age:" number value={measurements.age} onChangeText={setMeasurement('age')} />
        <Input prefix="Waist:" number suffix="cm" value={measurements.waistCircumference} onChangeText={setMeasurement('waistCircumference')} />
        <Input prefix="Neck:" number suffix="cm" value={measurements.neckCircumference} onChangeText={setMeasurement('neckCircumference')}/>
        <Input prefix="Wrist:" number suffix="cm" value={measurements.wristCircumference} onChangeText={setMeasurement('wristCircumference')}/>
        <Input prefix="Forearm:" number suffix="cm" value={measurements.forearmCircumference} onChangeText={setMeasurement('forearmCircumference')}/>
        <Input prefix="Hip:" number suffix="cm" value={measurements.hipCircumference} onChangeText={setMeasurement('hipCircumference')}/>

        <DropDownPicker
          style={styles.dropDownGeneric}
          textStyle={styles.dropDownText}
          containerStyle={styles.dropDown}
          dropDownContainerStyle={styles.dropDownContainerStyle}
          listMode="SCROLLVIEW"
          open={open}
          value={activityLevel}
          items={items}
          autoScroll
          itemKey='key'
          setOpen={setOpen}
          setValue={setActivityLevel}
          setItems={setItems}
        />

        <Button onPress={onSave} title={'Save'} />
      </ScrollView>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container: { flex: 1 },
  text: {
    fontSize: 32,
    color: 'white',
    fontWeight: 'bold',
    textAlign: 'center'
  },
  dropDownContainerStyle: {
    backgroundColor: 'rgb(173,227,226)',
    borderColor: 'transparent',
    margin: 32,
    padding: 32,
  },
  dropDownGeneric: {
    backgroundColor: 'rgb(173,227,226)',
    borderColor: 'transparent',

  },
  dropDown: {
    backgroundColor: 'rgb(173,227,226)',
    borderColor: 'white',
    borderWidth: 2,
    borderRadius: 32,
    margin: 32,
    width: 'auto',
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 10,
    padding: 32,
    zIndex: 10
  },
  dropDownText: {
    color: 'white',
    textShadowColor: 'black',
    fontSize: 18,
    fontStyle: 'italic',
    paddingHorizontal: 4
  },
})

export default Measurements