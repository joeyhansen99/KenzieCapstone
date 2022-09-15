import BaseClass from '../util/baseClass';
import axios from 'axios';

export default class CardClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getExample', 'createExample'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }


    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty('onReady')) {
            this.props.onReady();
        }
    }

    //describes this method doesnt execute until called
    async createCard(id, name, set, foil, fullArt, quantity, cost, color, type, rarity, errorCallback) {
        try {
            //const = constant
            const response = await this.client.post(`/cards`,
            {
                "id": id,
                "name": name,
                "set": set,
                "foil": foil,
                "fullArt": fullArt,
                "quantity": quantity,
                "cost": cost,
                "color": color,
                "type": type,
                "rarity": rarity,
            });
            return response.data;
        } catch (error) {
            this.showMessage('Card already exists in the collection');
            this.handleError('createCard', error, errorCallback);
        }
    }


    async getCard(id, errorCallback) {
        try {
            const response = await this.client.get(`/cards/${id}`);
            return response.data;
        } catch (error) {
            this.showMessage('Card could not be found');
            this.handleError('getCard', error, errorCallback);
        }
    }


    async deleteCard(id, errorCallback) {
        try {
            const response = await this.client.delete(`/cards/${id}`);
            return response.data;
        } catch (error) {
            this.handleError('deleteCard', error, errorCallback);
        }
    }


    handleError(method, error, errorCallback) {
        console.error(method + ' failed - ' + error);
        if(error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + ' failed - ' + error);
        }
    }
}
