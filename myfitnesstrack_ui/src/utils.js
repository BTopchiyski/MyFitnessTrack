import { Alert } from "react-native"


export const createMissingMeasurementsPrompt = (onPress) => {
  Alert.alert(
    'Missing data',
    'Please set measurements before setting goals',
    [
      {
        text: 'Cancel',
        style: 'cancel',
        onPress
      },
      {
        text: 'Ok',
        style: 'default',
        onPress
      },
    ],
  )
}

export const createMissingProgressEntriesPrompt = (onPress) => {
  Alert.alert(
    'Missing data',
    'Please add a progress entry to see progress',
    [
      {
        text: 'Cancel',
        style: 'cancel',
        onPress
      },
      {
        text: 'Ok',
        style: 'default',
        onPress
      },
    ],
  )
}

export const createAlert = (title, message) => {
  Alert.alert(
    title,
    message,
    [
      {
        text: 'Cancel',
        style: 'cancel',
      },
      {
        text: 'Ok',
        style: 'default',
      },
    ],
  )
}