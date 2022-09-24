import BaseClass from '../util/baseClass';
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";


class IndexPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['method names', 'secondMethodName'], this);
        this.cardDataStore = new DataStore();
    }


    async mount() {
        document.getElementById('name of element').addEventListener('submit or click', this.eventMethodName);

        this.cardClient = new CardClient();
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onCreateCardSubmit(event) {
        event.preventDefault();

        this.cardDataStore.set('card', null);
    }
}


const main = async () => {
    const indexPage = new IndexPage();
    indexPage.mount();
};

window.addEventListener('DOMContentLoaded', main);