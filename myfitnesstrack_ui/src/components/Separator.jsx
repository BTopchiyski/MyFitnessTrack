import { View, Text, ActivityIndicator, StyleSheet } from 'react-native'
import React, { useEffect } from 'react'

const Separator = () => {
    return (
        <View style={styles.separator} />
    )
}

const styles = StyleSheet.create({
    separator: {
        width: '90%',
        height: 2,
        backgroundColor: 'white',
    }
})


export default Separator