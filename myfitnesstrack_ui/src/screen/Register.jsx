import { View, Text, SafeAreaView, StyleSheet } from 'react-native'
import React, { useEffect, useState } from 'react'
import authClient from '../client/authClient'
import { isEmpty } from 'lodash'
import Input from '../components/Input'
import Button from '../components/Button'
import { ScrollView } from 'react-native-gesture-handler'
import DropDownPicker from 'react-native-dropdown-picker'
import { createAlert } from '../utils'


const Register = ({ navigation }) => {
  const [open, setOpen] = useState(false);
  const [firstname, setFirstName] = useState('')
  const [lastname, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [gender, setGender] = useState();

  const [genders, setGenders] = useState([
    { label: 'Male', value: 'MALE' },
    { label: 'Female', value: 'FEMALE' }
  ]);

  const register = async () => {
    if(isEmpty(firstname) ||
        isEmpty(lastname) ||
        isEmpty(email) ||
        isEmpty(password) || 
        isEmpty(gender)) {
            createAlert('Error', "Fields cannot be empty")
            return;
        }
    const response = await authClient.register({firstname, lastname, email, password, gender})
    if (!isEmpty(response) && isEmpty(response?.error)) {
      navigation.reset({ index: 0, routes: [{ name: 'Tabs' }] })
    }
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Input title="First name" onChangeText={setFirstName} />
      <Input title="Last name" onChangeText={setLastName} />
      <Input title="Email" onChangeText={setEmail} />
      <Input title="Password" onChangeText={setPassword} secure />
      <DropDownPicker
        placeholder='Select gender'
        style={styles.dropDownGeneric}
        textStyle={styles.dropDownText}
        containerStyle={styles.dropDown}
        dropDownContainerStyle={styles.dropDownContainerStyle}
        listMode="SCROLLVIEW"
        scrollViewProps={{ scrollEnabled: true }}
        items={genders}
        open={open}
        value={gender}
        setOpen={setOpen}
        setItems={setGenders}
        setValue={setGender}
      />
      <Button title="Register" onPress={register} />

    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: { justifyContent: 'center' },
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
    fontSize: 24,
    fontStyle: 'italic',
    paddingHorizontal: 4
  },
})

export default Register