import BaseClient from './baseClient'
import { isEmpty } from 'lodash'


class ProgressClient extends BaseClient {
    async setEntry(weight, calories) {
        return await this.makeRequest('progress', 'POST', { weight, calories })
    }
}

export default new ProgressClient()