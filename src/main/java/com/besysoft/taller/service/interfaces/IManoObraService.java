package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.ManoObra;

public interface IManoObraService {
    ManoObra altaManoObra(ManoObra manoObra);
    ManoObra modiManoObra(ManoObra manoObra, Long id);
}
