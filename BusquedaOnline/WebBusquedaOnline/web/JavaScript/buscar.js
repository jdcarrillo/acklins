/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

flickrapi = 'e9d0f27676af408745dfee4495a039ed';//introduce tu API key de flickr
max=5;//maximo 500
size='m';//posibles valores: s (pequeñol) , b (grande), m (mediano), t (miniatura)

function buscar(){
	query = document.getElementById('txt_buscar').value;
	url='http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key='+flickrapi+'&sort=relevance&text='+query+'&per_page='+max+'&format=json&jsoncallback=flickr';
	script=document.createElement('script');
	script.type='text/javascript';
	script.src=url;
	document.getElementsByTagName('head')[0].appendChild(script);
}

function flickr(obj){
	div_res = document.getElementById('resultat');
	if(obj){
		items=obj.photos.photo;
		if(items.length){
			cadena ='';
			for(i=0;i<items.length;i++){
				url='http://www.flickr.com/photos/'+items[i].owner+'/'+items[i].id;
				foto='http://static.flickr.com/'+items[i].server+'/'+items[i].id+'_'+items[i].secret+'_'+size+'.jpg';
				cadena+='<a href="'+url+'"><img src="'+foto+'"></a> ';
			}
		}else cadena ='sin fotos';
	}else cadena ='error';
	div_res.innerHTML = cadena;      
}
