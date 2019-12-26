package si.fri.prpo.predloge.api.v1;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;


@Path("predloge")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PredlogeVir{
  private HashMap<Integer,Integer> predloge=new LinkedHashMap<>();
  private Logger log=Logger.getLogger(PredlogeVir.class.getName());

  @PostConstruct
  public void init(){
    log.info("Sem živ.");
  }

  @GET
  public Response getPredloge(){
    log.info("halo");
    predloge.forEach((key,val)->log.info("key: "+key+" val: "+val));
    List<Integer> ret=new ArrayList<>();
    predloge.entrySet().stream().sorted(Map.Entry.comparingByValue((i1,i2)->i2-i1)).forEach(entry->ret.add(entry.getKey()));
    ret.forEach(el->log.info(" ID: "+el));
    return Response.ok(ret).build();
  }

  @PUT
  @Path("{id}")
  public Response updatePredloge(@PathParam("id") Integer id){
    log.info("id: "+id);
    Integer res=predloge.get(id);
    if(res!=null){
      log.info("oldval: "+res);
      res++;
      predloge.put(id,res);
      log.info("newval: "+predloge.get(id));
    } else{
      predloge.put(id,1);
      log.info("newval: "+predloge.get(id));
    }
    return Response.status(Response.Status.CREATED).build();
  }
}
