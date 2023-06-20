import BaseClient from './baseClient'
import { isEmpty } from 'lodash'


class GoalClient extends BaseClient {
    // Calorie Goals
    async getCurrent() {
        return await this.makeRequest('calculate/current');
    }
    async getMaintain() {
        return await this.makeRequest('calculate/maintain');
    }
    async getGain() {
        return await this.makeRequest('calculate/gain');
    }
    async getWeightloss() {
        return await this.makeRequest('calculate/loose');
    }
    async setCurrent(calorie_goal) {
        return await this.makeRequest('calculate', 'POST', { calorie_goal })
    }
    //
    // Macronutrient goals
    async getCurrentMacros() {
        return await this.makeRequest('macronutrient');
    }

    async getMacroGoals() {
        return await this.makeRequest('macronutrient/all');
    }

    async setMacroGoal(data) {
        const {error, ...rest} = data;
        return await this.makeRequest('macronutrient', 'POST', {
            ...rest
        })
    }
    //
}

export default new GoalClient()