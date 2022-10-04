import BaseClass from '../util/baseClass';
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";


class AddCardPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['method names', 'secondMethodName'], this);
        this.cardDataStore = new DataStore();
    }


    async mount() {
        document.getElementById('name of element').addEventListener('submit or click', this.eventMethodName);

        //this.cardClient = new CardClient();
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

        async renderExample() {
    //        let resultArea = document.getElementById("result-info");
    //
    //        const example = this.dataStore.get("example");
    //
    //        if (example) {
    //            resultArea.innerHTML = `
    //                <div>ID: ${example.id}</div>
    //                <div>Name: ${example.name}</div>
    //            `
    //        } else {
    //            resultArea.innerHTML = "No Item";
    //        }
        }
}


const main = async () => {
    const addcardPage = new AddCardPage();
    addcardPage.mount();
};

window.addEventListener('DOMContentLoaded', main);