import { View, Text, ActivityIndicator, StyleSheet } from 'react-native'
import React, { useEffect } from 'react'
import authClient from '../client/authClient';

const Launcher = ({ navigation }) => {
    useEffect(() => {
        (async () => {
            // Check for valid token
            const result = await authClient.check();
            console.log('userCheck', result)
            let nextPage = 'Login'
            if(result) {
                nextPage = 'Tabs'
            }
            navigation.reset({ index: 0, routes: [{ name: nextPage }] })
        })()
    }, [navigation]);

    return (
        <View style={styles.container}>
            <ActivityIndicator size={64} color={'white'} />
        </View>
    )
}

const styles = StyleSheet.create({
    container: { flex: 1, justifyContent: 'center' },
})


export default Launcher