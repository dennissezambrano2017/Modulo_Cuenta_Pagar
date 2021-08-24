/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


console.log(window.location.origin + '/Modulo_Cuenta_Pagar/resources/anticipos');


    
var app = new Vue({
    el: '#app',
    data: {
        name: 'Hello Vue!',
    },
    created: () => {
      fetch(window.location.origin + '/Modulo_Cuenta_Pagar/resources/anticipos')
        .then(res => res.json())
        .catch(err => console.log(err))
        .then(response => {
            console.log(response);
        })
    },
})