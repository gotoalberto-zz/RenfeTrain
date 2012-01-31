
Ext.define('adminIndemnizaciones.store.tipoTren',{
	extend:'Ext.data.Store',
	model: 'adminIndemnizaciones.model.tipoTren',
	proxy: {
		type: 'ajax',
		url: '/admin/tipotren.do',
		reader: {
			type: 'json',
			root: 'items',
			totalProperty: 'totalCount'
		}
	}
});

Ext.define('adminIndemnizaciones.store.indemnizacion',{
	extend:'Ext.data.Store',
	model: 'adminIndemnizaciones.model.indemnizacion',
	proxy: {
		type: 'ajax',
		url: '/admin/indemnizaciones.do',
		reader: {
			type: 'json',
			root: 'items',
			totalProperty: 'totalCount'
		}
	}
});