
Ext.define('consulta.store.llegadas',{
	extend:'Ext.data.Store',
	model: 'consulta.model.llegadas',
	proxy: {
		type: 'ajax',
		url: 'llegadas.do',
		reader: {
			type: 'json',
			root: 'items',
			totalProperty: 'totalCount'
		}
	}
});


Ext.define('consulta.store.estaciones',{
	extend:'Ext.data.Store',
	model: 'consulta.model.estaciones',
	proxy: {
		type: 'ajax',
		url: 'estaciones.do',
		reader: {
			type: 'json',
			root: 'estacion',
			totalProperty: 'totalCount'
		}
	}
});
