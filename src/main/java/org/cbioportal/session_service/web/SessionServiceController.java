/*
 * Copyright (c) 2016 Memorial Sloan-Kettering Cancer Center.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF MERCHANTABILITY OR FITNESS
 * FOR A PARTICULAR PURPOSE. The software and documentation provided hereunder
 * is on an "as is" basis, and Memorial Sloan-Kettering Cancer Center has no
 * obligations to provide maintenance, support, updates, enhancements or
 * modifications. In no event shall Memorial Sloan-Kettering Cancer Center be
 * liable to any party for direct, indirect, special, incidental or
 * consequential damages, including lost profits, arising out of the use of this
 * software and its documentation, even if Memorial Sloan-Kettering Cancer
 * Center has been advised of the possibility of such damage.
 */

/*
 * This file is part of cBioPortal Session Service.
 *
 * cBioPortal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.cbioportal.session_service.web;

import org.cbioportal.session_service.domain.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Manda Wilson 
 */
@RestController // shorthand for @Controller, @ResponseBody
@RequestMapping(value = "/api/sessions/")
public class SessionServiceController
{

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceController(SessionRepository sessionRepository)
    {
        this.sessionRepository = sessionRepository;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Session addSession(@RequestBody String data) 
    {
        return sessionRepository.save(new Session(data)); 
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Session> getSessions()
    {
        return sessionRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Session getSession(@PathVariable String id) 
    {
        return sessionRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Session updateSession(@PathVariable String id, @RequestBody String data)
    {
        Session savedSession = sessionRepository.findOne(id);
        savedSession.setData(data);
        return sessionRepository.save(savedSession);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSession(@PathVariable String id)
    {
        sessionRepository.delete(id);        
    } 
}