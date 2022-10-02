import BaseClass from '../util/baseClass';
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";


class IndexPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['onLoadPage', 'secondMethodName'], this);
        this.cardDataStore = new DataStore();
    }


    async mount() {
        document.getElementById('name of element').addEventListener('submit or click', this.eventMethodName);
        this.cardClient = new CardClient();
        this.onLoadPage();
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onLoadPage() {
         let target = document.getElementById('main_table').innerHTML;
         let tableData = await this.cardClient.getAllCards();
         this.cardDataStore.set('table', tableData);
         for (let p of tableData) {
            target += '<tr>';
            target += `<td>${p.quantity}</td>`;
            target += `<td>${p.name}</td>`;
            target += `<td>${p.set}</td>`;
            target += `<td>${p.cost}</td>`;
            target += `<td>${p.color}</td>`;
            target += `<td>${p.type}</td>`;
            target += `<td>${p.rarity}</td>`;
            target += `<td>${p.foil}</td>`;
            target += `<td>${p.fullArt}</td>`;
            target += '<tr>';
         }
         document.getElementById('main_table').innerHTML = target;
    }

}


const main = async () => {
    const indexPage = new IndexPage();
    indexPage.mount();
};

window.addEventListener('DOMContentLoaded', main);