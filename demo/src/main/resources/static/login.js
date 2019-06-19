import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI)

export default {
    data () {
        return {
            radio: '1'
        };
    }
}
new Vue({
    el: '#app',
    render: h => h(App)
});