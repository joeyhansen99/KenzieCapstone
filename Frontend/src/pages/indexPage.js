import BaseClass from '../util/baseClass';
import CardClient from "../api/cardClient";

//This page has no mounts or event listeners - I wasn't sure what you put here when you don't need it
class IndexPage extends BaseClass {
    constructor() {
        super();
//        this.bindClassMethods(['method names', 'secondMethodName'], this);
        this.cardDataStore = new DataStore();
    }

    async mount() {
//        document.getElementById('name of element').addEventListener('submit or click', this.eventMethodName);
    }
}


const main = async () => {
    const indexPage = new IndexPage();
    indexPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
