import BaseClient from './baseClient'
import { isEmpty } from 'lodash'


class AuthClient extends BaseClient {
    async _genericAuthenticate(endpoint, body) {
        const response = await this.makeRequest(endpoint, 'POST', { ...body }, { 'Authorization': null })
        if(!isEmpty(response?.access_token) && isEmpty(response?.error)) {
            await BaseClient.setBearerToken(response.access_token)
        }
        return response;
    }
    async login(email, password) {
        return await this._genericAuthenticate('auth/authenticate', { email, password })
    }

    async register({firstname, lastname, email, password, gender}) {
        return await this._genericAuthenticate('auth/register', {firstname, lastname, email, password, gender})
    }

    async logout() {
        await BaseClient.setBearerToken('')
        return await this.makeRequest('auth/logout', 'POST')
    }

    async check() {
        const response = await this.makeRequest('auth/user');
        if(isEmpty(response)) {
            // Clear local token
            await BaseClient.setBearerToken('');
            //
        }
        return response;
    }
}

export default new AuthClient()