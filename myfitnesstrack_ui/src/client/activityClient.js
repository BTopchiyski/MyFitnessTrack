import BaseClient from './baseClient'
import { isEmpty } from 'lodash'


class ActivityClient extends BaseClient {
    async getActivityLevel() {
        return await this.makeRequest('activity-level')
    }
    async setMeasurements(measurements) {
        return await this.makeRequest('measurement', 'POST', measurements)
    }

    async getMeasurements() {
        return await this.makeRequest('measurement');
    }
}

export default new ActivityClient()