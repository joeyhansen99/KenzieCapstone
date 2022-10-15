import BaseClass from '../util/baseClass';
import axios from 'axios';

export default class CardClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getCard', 'createCard', 'updateCard', 'deleteCard', 'getAllCards'];
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

    async getAllCards(errorCallback) {
        try {
            const response = await this.client.get(`/cards/all`);
            console.log('getAllCards');
            console.log(response.data);
            return response.data;
        } catch (error) {
            this.handleError('getAllCards', error, errorCallback);
        }
    }

    async createCard(id, name, set, foil, fullArt, quantity, cost, color, type, rarity, errorCallback) {
        try {
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
            console.log('createCard');
            console.log(response.data);
            return response.data;
        } catch (error) {
            this.showMessage('Card already exists in the collection');
            this.handleError('createCard', error, errorCallback);
        }
    }

    async updateCard(id, foil, fullArt, quantity, errorCallback) {
        try {
            const response = await this.client.patch(`/cards/${id}`, {
                id: `${id}`,
                foil: `${foil}`,
                fullArt: `${fullArt}`,
                quantity: `${quantity}`});
             console.log('updateCard');
             console.log(response.data);
            return response.data;
        } catch (error) {
            this.handleError('updateCard', error, errorCallback);
        }
    }

    async getCard(id, errorCallback) {
        try {
            const response = await this.client.get(`/cards/${id}`);
            console.log('getCard');
            console.log(response.data);
            return response.data;
        } catch (error) {
            this.handleError('getCard', error, errorCallback);
            console.log("get card - id not found")
        }
    }

    async deleteCard(id, errorCallback) {
        try {
            const response = await this.client.delete(`/cards/${id}`);
            // console.log('deleteCard');
            // console.log(response.data);
            return response.data;
        } catch (error) {
            this.handleError('deleteCard', error, errorCallback);
        }
    }

    async getCardData(name, errorCallback) {
        try {
            const response = await this.client.get(`/cards/{cardId}`);
            // console.log('getCardData');
            // console.log(response.data);
            return response.data;
        } catch (error) {
            this.handleError('getCardData', error, errorCallback);
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
