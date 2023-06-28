import BaseClient from './baseClient'
import { isEmpty } from 'lodash'


class ProgressClient extends BaseClient {
    async setEntry(weight, calories) {
        return await this.makeRequest('progress', 'POST', { weight, calories })
    }

    async getWeeklyEntry() {
        return await this.makeRequest('progress/user/weekly')
    }

    async getMonthlyEntry() {
        return await this.makeRequest('progress/user/monthly')
    }
}

export default new ProgressClient()