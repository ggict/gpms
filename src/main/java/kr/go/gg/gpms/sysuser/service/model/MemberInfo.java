package kr.go.gg.gpms.sysuser.service.model;

 
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class MemberInfo extends SysUserVO implements UserDetails {
	private Set<GrantedAuthority> authorities;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6495720862778099166L;
	public MemberInfo(){
		
	}
	public MemberInfo(String id, String password, String name, Collection<? extends GrantedAuthority> authorities){
		this.setUSER_ID(id);
		this.setSECRET_NO(password);
		this.setUSER_NM(name);
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}
	public void setId(String id){
		setUSER_ID(id);
	}
	
	public void setPassword(String password){
		this.setSECRET_NO(password);
	}
	
	@Override
	public String getPassword() {
		
		return getSECRET_NO();
	}

	@Override
	public String getUsername() {
		
		return getUSER_ID();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

	/**
	 * 관리자 구분 (Y)
	 * @return
	 */
	public String getIS_ADMIN() {
		if("URSE0001".equals(getUSER_SE_CODE()) ){
			for (GrantedAuthority grantedAuthority : authorities) {
				if("ROLE_ADMIN".equals(grantedAuthority.getAuthority())){
					return "Y";
				}
	        }
		}
		return "N";
	}
	
	/**
	 * 외부공사업체 구분(Y)
	 * @return
	 */
	public String getIS_EXT() {
		if("URSE0001".equals(getUSER_SE_CODE()))
			return "N";
		return "Y";
	}
	

}
