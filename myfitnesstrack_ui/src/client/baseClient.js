const { default: AsyncStorage } = require("@react-native-async-storage/async-storage");
const { SERVER_URL, BEARER_TOKEN_STORAGE_KEY } = require("./constants");


export default class BaseClient {
    static async setBearerToken(token) {
        await AsyncStorage.setItem(BEARER_TOKEN_STORAGE_KEY, token)
    }

    async getBearerToken() {
        try {
            return await AsyncStorage.getItem(BEARER_TOKEN_STORAGE_KEY);
        } catch (error) {
            console.error(error)
            return null;   
        }
    }

    async makeRequest(endpoint = '', method = 'GET', body = null, headers = null) {
        try {
            const url = `${SERVER_URL}/${endpoint}`
            const defaultHeaders = {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${await this.getBearerToken()}`
            }
            const response = await fetch(url, { method, headers: { ...defaultHeaders, ...headers }, body: JSON.stringify(body) })
            const responseJson = await response.json()
            return responseJson
        } catch (error) {
            console.error(error);
            return { error }
        }
    }
}